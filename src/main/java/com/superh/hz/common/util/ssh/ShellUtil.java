package com.superh.hz.common.util.ssh;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


/**
 * shell工具类
 */
public class ShellUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ShellUtil.class);
	
	/**
	 * 后台执行命令
	 * @param command String, 命令
	 * @param user String, 用户
	 * @param passwd String, 密码
	 * @param address String, 地址
	 */
	public static void nohupExecuteCommand(String command, String user, String passwd, String address) {
		
		nohupExecuteCommand(command, user, passwd,address,-1);

	}
	
	/**
	 * 后台执行命令
	 * @param command String, 命令
	 * @param user String, 用户
	 * @param passwd String, 密码
	 * @param address String, 地址
	 * @param port int, 端口
	 */
	public static void nohupExecuteCommand(String command, String user, String passwd, String address, int port) {
		if(!command.endsWith("&")){
			command += "&";
		}
		Session session = null;
		ChannelExec channelExec = null;
		JSch jsch = new JSch();
		try {
			if (port <= 0) {
				session = jsch.getSession(user, address);
			} else {
				session = jsch.getSession(user, address, port);
			}
		} catch (Exception e) {
			logger.error("failed to ssh get session !",e);
			return;
		}

		if (session == null) {
			logger.error("failed to ssh get session,session is null !");
			return;
		}

		session.setPassword(passwd);
		session.setConfig("StrictHostKeyChecking", "no");

		List<StringBuffer> list = new ArrayList<StringBuffer>();
		try {

			session.connect(30000);

			channelExec = (ChannelExec) session.openChannel("exec");
			channelExec.setCommand(command);
			// 获取ssh错误输出
			//InputStream errin = channelExec.getErrStream();
			// 获取ssh标准输出
			//InputStream in = channelExec.getInputStream();
			channelExec.connect();
			Thread.sleep(2000);
			channelExec.disconnect();
			session.disconnect();

		} catch (Exception e) {
			logger.error("failed to execute command !",e);
			return;
		}

	}

	/**
	 * 执行命令
	 * @param command String, 命令
	 * @param user String, 用户
	 * @param passwd String, 密码
	 * @param address String, 地址
	 * @return List<StringBuffer>,标准输出和错误输出
	 */
	public static List<StringBuffer> executeCommand(String command, String user, String passwd, String address) {
		
		return executeCommand(command,user,passwd,address,-1);
	
	}
	
	/**
	 * 执行命令
	 * @param command String, 命令
	 * @param user String, 用户
	 * @param passwd String, 密码
	 * @param address String, 地址
	 * @param port int, 端口
	 * @return List<StringBuffer>,标准输出和错误输出
	 */
	public static List<StringBuffer> executeCommand(String command, String user, String passwd, String address, int port) {
		Session session = null;
		ChannelExec channelExec = null;
		// int port=-1;
		JSch jsch = new JSch();
		try {
			if (port <= 0) {
				// 连接服务器，采用默认端口
				session = jsch.getSession(user, address);
				// session = jsch.getSession("root", "192.168.11.71");
			} else {
				// 采用指定的端口连接服务器
				session = jsch.getSession(user, address, port);
			}
		} catch (Exception e) {
			logger.error("failed to ssh get session !", e);
			return null;
		}

		// 如果服务器连接不上，则抛出异常
		if (session == null) {
			logger.error("failed to ssh get session,session is null !");
			return null;
		}

		// 设置登陆主机的密码
		session.setPassword(passwd);// 设置密码

		// 设置第一次登陆的时候提示，可选值：(ask | yes | no)
		session.setConfig("StrictHostKeyChecking", "no");

		List<StringBuffer> list = new ArrayList<StringBuffer>();
		try {
			// 设置登陆超时时间
			session.connect(30000);

			// 创建ssh通信通道
			channelExec = (ChannelExec) session.openChannel("exec");
			// 输入命令
			// channelExec.setCommand("cd /home/hadoop/wangyongfei \n source
			// ./.bash_profile \n ls -l");
			channelExec.setCommand(command);
			// 获取ssh错误输出
			InputStream errin = channelExec.getErrStream();
			// 获取ssh标准输出
			InputStream in = channelExec.getInputStream();
			channelExec.connect();
			int res = -1;
			StringBuffer buf = new StringBuffer(1024);
			byte[] tmp = new byte[1024];
			StringBuffer errbuf = new StringBuffer(1024);
			byte[] errtmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					buf.append(new String(tmp, 0, i));
				}
				while (errin.available() > 0) {
					int i = errin.read(errtmp, 0, 1024);
					if (i < 0)
						break;
					errbuf.append(new String(errtmp, 0, i));
				}

				if (channelExec.isClosed()) {
					res = channelExec.getExitStatus();
					//System.out.println( res );
					break;
				}
				// this.wait( 100 );
			}

			//System.out.println("!!!!!!!!!"+ buf.toString() );
			//System.out.println("*******" + errbuf.toString() );

			list.add(buf);
			list.add(errbuf);
			channelExec.disconnect();
			session.disconnect();
			// System.out.println(res);
		} catch (Exception e) {
			logger.error("failed to execute command !", e);
			return null;
		}
		return list;
	}
}
