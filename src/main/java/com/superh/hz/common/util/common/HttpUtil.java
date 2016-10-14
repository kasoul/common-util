package com.superh.hz.common.util.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * HTTP 获取网页地址
 */
public class HttpUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * get方式获取url内容,兼容重定向
	 * 
	 * @param url String, 网址
	 * @return String, 网页内容
	 * @throws Exception
	 */
	public static String fetchGetURLContentRedirect(String url) throws Exception {
		return fetchURLContentRedirect(url, "UTF-8", "GET", null);
	}

	/**
	 * get方式获取url内容
	 * 
	 * @param url String, 网址
	 * @return String, 网页内容
	 * @throws Exception
	 */
	public static String fetchGetURLContent(String url) throws Exception {
		return fetchURLContent(url, "UTF-8", "GET", null);
	}

	/**
	 * get方式获取url内容
	 * 
	 * @param url
	 *            网址
	 * @param charset
	 *            编码方式
	 * @param params
	 *            参数 new NameValuePair("id", "youUserName")
	 * @return 网页内容
	 * @throws Exception
	 */
	public static String fetchGetURLContent(String url, String charset, NameValuePair... params) throws Exception {
		return fetchURLContent(url, charset, "GET", params);
	}

	/**
	 * post方式获取url内容
	 * 
	 * @param url
	 *            网址
	 * @param charset
	 *            编码方式
	 * @param params
	 *            参数 new NameValuePair("id", "youUserName")
	 * @return 网页内容
	 * @throws Exception
	 */
	public static String fetchPostURLContent(String url, String charset, NameValuePair... params) throws Exception {
		return fetchURLContent(url, charset, "POST", params);
	}

	/**
	 * 下载地址到本地
	 * 
	 * @param url
	 *            网址
	 * @param savePath
	 *            本地存储绝对路径
	 * @throws Exception
	 */
	public static void downloadURLContent(String url, String savePath) throws Exception {
		try {
			HttpClient client = new HttpClient();

			// client.getHostConfiguration().setProxy("192.168.200.254", 3128);

			int ctimeout = 5000;
			int stimeout = 55000;
			client.getHttpConnectionManager().getParams().setConnectionTimeout(ctimeout);
			client.getHttpConnectionManager().getParams().setSoTimeout(stimeout);
			GetMethod get = new GetMethod(url);
			client.executeMethod(get);
			FileOutputStream output = new FileOutputStream(savePath);
			IOUtils.copy(get.getResponseBodyAsStream(), output);
			output.close();
			get.releaseConnection();
		} catch (Exception e) {
			logger.error("download from URLContent error,url is [{}]", url, e);
		}
	}

	public static String postXMLContent(String url, String xml, String charset) throws Exception {

		PostMethod method = new PostMethod(url);
		HttpClient client = new HttpClient();

		// client.getHostConfiguration().setProxy("192.168.200.254", 3128);

		String ret = null;
		int ctimeout = 5000;
		int stimeout = 55000;

		client.getHttpConnectionManager().getParams().setConnectionTimeout(ctimeout);
		client.getHttpConnectionManager().getParams().setSoTimeout(stimeout);

		try {
			method.setRequestEntity(new StringRequestEntity(xml, "text/xml", charset));
			client.executeMethod(method);

			InputStream is = method.getResponseBodyAsStream();
			java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
			IOUtils.copy(is, bos);
			ret = bos.toString(charset);

			bos.close();
			is.close();

		} catch (Exception e) {
			logger.error("post xml content error,url is [{}]", url, e);
		} finally {
			method.releaseConnection();
		}
		return ret;
	}

	private static String fetchURLContent(String url, String charset, String methodType, NameValuePair... params)
			throws HttpException, IOException {

		HttpClient client = new HttpClient();

		// client.getHostConfiguration().setProxy("192.168.200.254", 3128);
		// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
		HttpMethod method;
		if ("GET".equals(methodType))
			method = new GetMethod(url);
		else
			method = new PostMethod(url);
		if (params != null && params.length > 0)
			method.setQueryString(params);
		// method.setFollowRedirects(true);
		method.getParams().setContentCharset("GBK");
		method.setRequestHeader("Accept-Language", "zh-cn");
		method.setRequestHeader("Accept-Encoding", " gzip, deflate");

		int ctimeout = 5000;
		int stimeout = 55000;
		client.getHttpConnectionManager().getParams().setConnectionTimeout(ctimeout);
		client.getHttpConnectionManager().getParams().setSoTimeout(stimeout);
		client.executeMethod(method);
		String acceptEncoding = "";
		acceptEncoding = method.getResponseHeader("Content-Encoding").getValue();

		String ret;
		if (acceptEncoding.toLowerCase().indexOf("gzip") > -1) {
			InputStream is = method.getResponseBodyAsStream();
			GZIPInputStream gzin = new GZIPInputStream(is);
			java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
			IOUtils.copy(gzin, bos);
			ret = bos.toString(charset);
			bos.close();
			gzin.close();
		} else {
			InputStream is = method.getResponseBodyAsStream();
			java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
			IOUtils.copy(is, bos);
			ret = bos.toString(charset);
			bos.close();
			is.close();
		}

		method.releaseConnection();
		return ret;
	}

	private static String fetchURLContentRedirect(String url, String charset, String methodType,
			NameValuePair... params) throws HttpException, IOException {

		HttpClient client = new HttpClient();

		// client.getHostConfiguration().setProxy("192.168.200.254", 3128);
		// 使用GET方法，如果服务器需要通过HTTPS连接，那只需要将下面URL中的http换成https
		HttpMethod method;
		if ("GET".equals(methodType))
			method = new GetMethod(url);
		else
			method = new PostMethod(url);
		if (params != null && params.length > 0)
			method.setQueryString(params);
		// method.setFollowRedirects(true);
		method.getParams().setContentCharset("GBK");
		method.setRequestHeader("Accept-Language", "zh-cn");
		method.setRequestHeader("Accept-Encoding", " gzip, deflate");

		int ctimeout = 5000;
		int stimeout = 55000;
		client.getHttpConnectionManager().getParams().setConnectionTimeout(ctimeout);
		client.getHttpConnectionManager().getParams().setSoTimeout(stimeout);
		client.executeMethod(method);

		// 检查是否重定向
		int statuscode = method.getStatusCode();
		if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
				|| (statuscode == HttpStatus.SC_SEE_OTHER) || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
			// 读取新的URL地址
			Header header = method.getResponseHeader("location");
			if (header != null) {
				String newuri = header.getValue();
				if ((newuri == null) || (newuri.equals("")))
					newuri = "/";
				GetMethod redirect = new GetMethod(newuri);
				client.executeMethod(redirect);
				// System.out.println("Redirect:"+
				// redirect.getStatusLine().toString());
				method = redirect;
			}
		}

		// 检查是否刷新
		Header refresh = method.getResponseHeader("Refresh");
		if (refresh != null) {
			String characteristicString = "url=";
			int subIndex = refresh.getValue().lastIndexOf(characteristicString) + characteristicString.length();
			String newuri = refresh.getValue().substring(subIndex);
			GetMethod redirect = new GetMethod(newuri);
			client.executeMethod(redirect);
			method = redirect;
		}

		Header acceptEncoding = method.getResponseHeader("Content-Encoding");

		String ret;
		if (acceptEncoding != null && acceptEncoding.getValue().toLowerCase().indexOf("gzip") > -1) {
			InputStream is = method.getResponseBodyAsStream();
			GZIPInputStream gzin = new GZIPInputStream(is);
			java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
			IOUtils.copy(gzin, bos);
			ret = bos.toString(charset);
			bos.close();
			gzin.close();
		} else {
			InputStream is = method.getResponseBodyAsStream();
			java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
			IOUtils.copy(is, bos);
			ret = bos.toString(charset);
			bos.close();
			is.close();
		}

		method.releaseConnection();
		return ret;
	}

	public static void main(String[] args) {
		try {
			// ?serviceCode=3008&areaId=7000&startDate=2015-03-04&endDate=2015-09-01
			// http://open.pc120.com/download/?q=aHR0cDovL2Rvd25maWxlLmRsb2FkMDAxMTIyLmluZm8vMjAxMV8xXzE4LzcvbTExMS5leGU=&appkey=YXNkZmFzZGZqYXM&timestamp=1295430113.546&sign=7c99d5fb17033be491f4cff33b4d944b
			NameValuePair param1 = new NameValuePair("domain", "http://bbs.taobao.com");
			NameValuePair param2 = new NameValuePair("key", "e8653d8956536b7ee9fdc538be7bb707");
			NameValuePair param3 = new NameValuePair("endDate", "2015-09-01");
			NameValuePair param4 = new NameValuePair("areaId", "7000");
			System.out.println(HttpUtil.fetchGetURLContent(
					"http://apis.juhe.cn/webscan/?domain=juhe.cn&key=e8653d8956536b7ee9fdc538be7bb707", "UTF-8", param1,
					param2, param3, param4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}