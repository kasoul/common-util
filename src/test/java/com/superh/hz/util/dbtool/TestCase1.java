package com.superh.hz.util.dbtool;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.superh.hz.common.util.config.PropertyUtil;
import com.superh.hz.common.util.dbtool.MySqlOpration;


/**
 * 功能说明：测试用例
 * 成员属性
 * 成员方法：测试用例
 * @author haungchao  2014-08-08
 * @update   
 */
public class TestCase1 {
	
	    /*properties配置文件读取测试*/
        public void testproperties(){
        	String path = this.getClass().getResource("/").toString();
        	try {
				path = java.net.URLDecoder.decode(path,"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    	PropertyUtil.readValue("config/mysql_zh_CN.properties", "localhost_url");
	    	
	    	PropertyUtil.readProperties("config/mysql_zh_CN.properties");
        }
	       
        /*获取系统路径*/
       	
        public void testclasspath() throws UnsupportedEncodingException{
	    	URL path1=this.getClass().getResource(""); //获取到包路径
	    	String path2=new String(this.getClass().getResource("/").getPath());//获取到classpath：bin路径
	    	path2 = java.net.URLDecoder.decode(path2,"utf-8");  //处理中文乱码
	    	System.out.println(path1.toString());
	    	System.out.println(path2);
	    	System.out.println(System.getProperty("user.dir"));
        }
        
        /*mysql查询测试*/
        public void testmysql(){
           String url=PropertyUtil.readValue("config/mysql_zh_CN.properties", "localhost_url");
           String username=PropertyUtil.readValue("config/mysql_zh_CN.properties", "localhost_username");
           String password=PropertyUtil.readValue("config/mysql_zh_CN.properties", "localhost_password");
           MySqlOpration myop = new MySqlOpration();
           myop.setConnectionParam(url,username,password);
     	   ResultSet rt = myop.query("select * from dc_app limit 0,1000");/*分页查询的第一条记录是从0下表位置开始的*/
     	   try {
     		while(rt.next()){
     			  System.out.println(rt.getString(3));/*以index获取时字段号从1开始，获取的值对应特定格式*/
     		   }
     	    } catch (SQLException e){
     		// TODO Auto-generated catch block
     		e.printStackTrace();
     	    }
        }
        
        /* 测试单元分隔符 */
    	public void testCharSpilt() {
    		String value = "30000286903430e38939b2fcd6fc95";
    		String fieldSeperator = String.valueOf((char) 0X1F);
    		System.out.println(value.split(fieldSeperator)[0]);
    		System.out.println(value.split(fieldSeperator)[1]);
    	}
    	
    	/* 测试字符串分隔 */
    	public void testStringSpilt(){
    		String[] yReports = "".split("%%");
    		String[] tReports = "ada项目::项目详细设计%%音乐基地项目::项目测试阶段%%".split("%%");

    		Map<String,String> yReports_map = new HashMap<String,String>();
    		System.out.println(yReports.length);
    		for(int i=0;i<yReports.length;i++)
    		{
    			String[] report_item = yReports[i].split("::");
    			System.out.println(report_item[0]);
    			System.out.println(report_item[1]);
    			yReports_map.put(report_item[0], report_item[1]);
    		}
    		
    	}
        

    
}
