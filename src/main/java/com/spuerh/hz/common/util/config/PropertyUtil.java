package com.spuerh.hz.common.util.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * 2015-8-27
 */
public class PropertyUtil {
	
	private static final Logger log = LoggerFactory.getLogger(PropertyUtil.class);
	private static Properties p =  new Properties();
	
	public static void init(String file){
		try {
			//String path = getJarPath();
			p.load(new FileInputStream(file));
			
		} catch (IOException e) {
			log.error("配置文件加载失败", e);
		}
	}
	
	
	public static String getUnicodeValue(String key){
		
		Object unicode = p.get(key);
		
		if(unicode == null ||"".equals(unicode)) return "utf-8";
		
		return unicode.toString();
	}
	
	
	public static String getUrlRegex(){
		
		return p.get("regex").toString();
	}
	
	public static String getJarPath(){
		String path = PropertyUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		try{  
	        path = java.net.URLDecoder.decode(path, "UTF-8");//转换处理中文及空格  
	    }catch (java.io.UnsupportedEncodingException e){
	    	log.error(e.getMessage(), e);
	    }
		if (path.endsWith(".jar")){
			path = path.substring(0, path.lastIndexOf("/") + 1);
		}
		return path;
	}
	
	 /** 
     * 根据filePath，key来获取value
     * @param filePath classpath下的路径文件名，key key名
     * @return 返回的value值 
     */  
	public static String readValue(String filePath,String key) {
		  Properties props = new Properties();
		        try {
		       //InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		       InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream(filePath); 
		       //filePath直接在src下的路径文件名，也就是在bin(classpath)下
		       //InputStream in = Object.class.getResourceAsStream("filePath");
		       //System.out.println(new File(".").getAbsolutePath());.//打印当前的绝对路径，也就输入相对路径时在哪个路径下
		         props.load(in);
		         String value = props.getProperty(key);
		            //System.out.println(key+":"+value);
		            return value;
		        } catch (Exception e) {
		         e.printStackTrace();
		         return null;
		        }
	}
	
	
	/** 
     * 根据filePath获取所有值
     * @param filePath classpath下的路径文件名
     * @return 返回的value值 
     */  
	public static  Map<String,String> readProperties(String filePath) {
	    Properties props = new Properties();
	    Map<String,String>  configmap = new HashMap<String,String>();
	    String key = null;
        String Property = null;
	        try {
	         InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream(filePath); 
	         props.load(in);
	            Enumeration<?> en = props.propertyNames();
	             while (en.hasMoreElements()) {
	            	 	key = (String) en.nextElement();
	                    Property = props.getProperty (key);
	                    configmap.put(key, Property);
	                   // System.out.println(key+Property);
	                }
	            return configmap;
	        } catch (Exception e) {
	         e.printStackTrace();
	        }
		return configmap;
	}
	
	/**
	 * 	this.getClass().getResource("");  //获取到包路径
	 *	this.getClass().getResource("/").getPath();  //获取到classpath：bin路径
	 *	path2 = java.net.URLDecoder.decode(path2,"utf-8");  //处理中文乱码
     */  
	public static void main(String[] args) {
		System.out.println(PropertyUtil.getJarPath());
		System.out.println(PropertyUtil.class.getResource("/").getPath());
		System.out.println(PropertyUtil.class.getClassLoader().getResourceAsStream("conf"));
	}

}
