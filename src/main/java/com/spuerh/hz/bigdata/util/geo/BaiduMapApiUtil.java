package com.spuerh.hz.bigdata.util.geo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * @File: BaiduMapApiUtil.java
 * @Date: 2015年1月20日
 * @Author: 程实
 */

public class BaiduMapApiUtil
{
	private static final double EARTH_RADIUS = 6378137;//赤道半径(单位m)
	
	private static final String[] akGeoCodingWeb = {"EGzMt1OAlWZsSToxNxy5U8rM","sGnvSp1jq8IQtzXSwUj8fsVe"};
	private static final int akGeoCodingWebLength = akGeoCodingWeb.length;
	
	private static final String[] akCoordTransf = {"w0LhxdWYM3qvHAsFaSs4IQdc", "EYynIFBVynGyWVV7woQ4y0k0"};
	private static final int akCoordTransfLength = akCoordTransf.length;

	private static final Random random = new Random();
	
	/*
	 * 将给定的地址转换为经纬度
	 * 采用baidu的Geocoding API
	 * http://developer.baidu.com/map/index.php?title=webapi/guide/webservice-geocoding
	 * @param ak
	 * @param address 需要转换的地址
	 * @return 成功获取经纬度，返回Map,map中的longitude键表示经度，latitude表示维度，否则，返回null
	 */
	public static Map<String,Double> getLongitudeAndLatitude(String address)
	{
		String ak = akGeoCodingWeb[random.nextInt(akGeoCodingWebLength)];
		String output = "json";
		//String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=" + output + "&ak=" + ak;
		StringBuilder strBuilder = new StringBuilder(150);
		strBuilder.append("http://api.map.baidu.com/geocoder/v2/?address=")
		.append(address).append("&output=").append(output).append("&ak=").append(ak);
		
		String result = urlParse(strBuilder.toString());
		if (null == result)
        {
        	return null;
        }

		double[] lngLat = baiduMapLngLatJsonParse(result);
		if (null != lngLat)
    	{
			Map<String,Double> map = new HashMap<String,Double>();
			map.put("longitude", lngLat[0]);
			map.put("latitude", lngLat[1]);
    		return map;
    	}
    	
		return null;
	}
	
	/*
     * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下 
     * @param longitude1 经度1
     * @param latitude1 维度1
     * @param longitude2 经度2
     * @param latitude2 维度2
     * @return 返回的距离，单位m
     */
	public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2)
	{
		double radLng1 = rad(longitude1);
		double radLat1 = rad(latitude1);
		double radLng2 = rad(longitude2);
		double radLat2 = rad(latitude2);
		
		double a = radLat1 - radLat2;
		double b = radLng1 - radLng2;
		double s = 2*Math.asin(
				Math.sqrt(
						Math.sin(a/2) * Math.sin(a/2) + 
						Math.cos(radLat1) * Math.cos(radLat2) * Math.sin(b/2) * Math.sin(b/2)
						)
				) * EARTH_RADIUS;
		return s;
	}

    /*
     * 将其他格式的经纬度坐标转换为baidu的经纬度坐标
     * 采用baidu的坐标转换API
     * http://developer.baidu.com/map/changeposition.htm
     * 同一个GPS坐标多次转为百度坐标时，每次转换结果都不完全一样，误差在2米范围内，属于正常误差，不影响正常使用。(摘自baidu官方说明)
     * @param longitude 经度
     * @param latitude 纬度
     * @param from 经纬度来源,	1：GPS设备获取的角度坐标;东经73-137，北纬1-55左右，百度api只能转化这个范围，差不多是中国的覆盖面积
     * 						2：GPS获取的米制坐标、sogou地图所用坐标;
     * 						3：google地图、soso地图、aliyun地图、mapabc地图和amap地图所用坐标
     * 						4：3中列表地图坐标对应的米制坐标
     * 						5：百度地图采用的经纬度坐标
     * 						6：百度地图采用的米制坐标
     * 						7：mapbar地图坐标;
     * 						8：51地图坐标 
     * @return 转换成功，返回Map,map中的longitude键表示经度，latitude表示维度，否则，返回null
     */
	public static Map<String,Double> transferToBaiduCoordinate(double longitude, double latitude, int from)
    {
    	String ak = akCoordTransf[random.nextInt(akCoordTransfLength)];
    	StringBuilder strBuilder = new StringBuilder(150);
		strBuilder.append("http://api.map.baidu.com/geoconv/v1/?coords=")
		.append(longitude).append(",").append(latitude).append("&from=").append(from)
		.append("&to=").append(5).append("&ak=").append(ak);
    	String result = urlParse(strBuilder.toString());
    	
    	if (null == result)
    	{
    		return null;
    	}
    	
    	double[] lngLat = baiduCoordTransfJsonParse(result);
    	if (null != lngLat)
    	{
    		Map<String,Double> map = new HashMap<String,Double>();
			map.put("longitude", lngLat[0]);
			map.put("latitude", lngLat[1]);
    		return map;
    	}
    
    	return null;
    }

	/*
	 * 将url解析成对应的位置信息
	 * @param url 指定位置对应的url
	 * @return 返回url对应的位置信息
	 */
	private static String urlParse(String url)
	{
		try
		{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
	        HttpResponse response = httpclient.execute(httpget);
	        HttpEntity entity = response.getEntity();
	        String result = EntityUtils.toString(entity);
	        
	        return result;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * (°)转化为弧度(rad)
	 */
    private static double rad(double d)  
    {  
       return d * Math.PI / 180.0;
    }
    
    /*
     * baidu Geocoding API返回的经纬度解析
     * @param jsonStr json格式的数据
     * @return 返回解析后的经纬度的坐标
     */
    private static double[] baiduMapLngLatJsonParse(String jsonStr)
    {
    	try
    	{
    		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
	    	int status = Integer.parseInt(jsonObject.getString("status"));
	    	if (0 != status)
	    	{
	    		return null;
	    	}
	    	
	    	double[] lngLat = new double[2];
	    	String result = jsonObject.getString("result");
	    	jsonObject = JSONObject.fromObject(result);
	    	String locatioin = jsonObject.getString("location");
	    	jsonObject = JSONObject.fromObject(locatioin);
	    	lngLat[0] = jsonObject.getDouble("lng");
	    	lngLat[1] = jsonObject.getDouble("lat");
	    	return lngLat;
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /*
     * baidu坐标转换API返回的经纬度解析
     * @param jsonStr json格式的返回结果
     * @return 解析后的经纬度
     */
    private static double[] baiduCoordTransfJsonParse(String jsonStr)
    {
    	try
    	{
    		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
	    	int status = Integer.parseInt(jsonObject.getString("status"));
	    	if (0 != status)
	    	{
	    		return null;
	    	}
	    	double[] lngLat = new double[2];
	    	String result = jsonObject.getString("result");
	    	jsonObject = JSONObject.fromObject(result.substring(1, result.length()-1));
	    	lngLat[0] = jsonObject.getDouble("x");
	    	lngLat[1] = jsonObject.getDouble("y");
    		return lngLat;
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		return null;
    	}
    }
    
    /**
	 * 将经纬度的小数点后4位的数字截断
	 * @param String coordinate
	 * @return String coordinate
	 */
	public static String coordinatePrecision(String coordinate)
	{
		String[] coordSplit = coordinate.split(",");
		if (coordSplit.length != 2)
			return null;
		String lng = coordSplit[0];
		String lat = coordSplit[1];
		return lng.substring(0, Math.min(lng.indexOf(".") + 7, lng.length())) + "," + 
			   lat.substring(0, Math.min(lat.indexOf(".") + 7, lat.length()));
	}
    
    public static void main(String[] args) {
    	System.out.println(BaiduMapApiUtil.getLongitudeAndLatitude("浙江省杭州市xh区"));
    }

}
