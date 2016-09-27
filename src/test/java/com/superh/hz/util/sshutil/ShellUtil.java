package com.superh.hz.util.sshutil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
/**
 * shell工具类 
 */
public class ShellUtil {
	public static List<StringBuffer> sshShell(String command, String user, String passwd, String ip,int port) throws Exception{
	    Session session = null;
	    ChannelExec channelExec = null;
	    //int port=-1;	  
	    JSch jsch = new JSch();	 	  	     
	    if(port <=0){
	        //连接服务器，采用默认端口
	        session = jsch.getSession(user, ip);
	    	//session = jsch.getSession("root", "192.168.11.71");
	    }else{
	        //采用指定的端口连接服务器
	        session = jsch.getSession(user, ip ,port);
	    }
	 
	    //如果服务器连接不上，则抛出异常
	    if (session == null) {
	        throw new Exception("session is null");
	    }
	     
	    //设置登陆主机的密码
	    session.setPassword(passwd);//设置密码   
	    
	    //设置第一次登陆的时候提示，可选值：(ask | yes | no)
	    session.setConfig("StrictHostKeyChecking", "no");
	    //设置登陆超时时间   
	    session.connect(30000);
	         
	        //创建ssh通信通道
	    	channelExec = (ChannelExec)session.openChannel("exec");
	    	//输入命令
	    	//channelExec.setCommand("cd /home/hadoop/wangyongfei \n source ./.bash_profile \n ls -l");
	    	channelExec.setCommand(command);	          
	    	//获取ssh错误输出
	        InputStream errin = channelExec.getErrStream();
	        //获取ssh标准输出
	        InputStream in = channelExec.getInputStream();  
	        channelExec.connect();
	        int res = -1;  
	        StringBuffer buf = new StringBuffer( 1024 );  
	        byte[] tmp = new byte[ 1024 ];  
	        StringBuffer errbuf = new StringBuffer( 1024 );  
	        byte[] errtmp = new byte[ 1024 ];  
	        while ( true ) {  
	            while ( in.available() > 0 ) {  
	                int i = in.read( tmp, 0, 1024 );  
	                if ( i < 0 ) break;  
	                buf.append( new String( tmp, 0, i ) );  
	            }  
	            while ( errin.available() > 0 ) {  
	                int i = errin.read( errtmp, 0, 1024 );  
	                if ( i < 0 ) break;  
	                errbuf.append( new String( errtmp, 0, i ) );  
	            }  
	            
	            if ( channelExec.isClosed() ) {  
	                res = channelExec.getExitStatus();  
	                //System.out.println( res );  
	                break;  
	            }  
	           // this.wait( 100 );  
	        } 
	       // System.out.println("!!!!!!!!!"+ buf.toString() ); 
	       // System.out.println("*******" +errbuf.toString() );  
	        List<StringBuffer> list=new ArrayList<StringBuffer>();
	        list.add(buf);
	        list.add(errbuf);
	        channelExec.disconnect();
	        session.disconnect();
	        //System.out.println(res);
	        return list;  
	}		
}
