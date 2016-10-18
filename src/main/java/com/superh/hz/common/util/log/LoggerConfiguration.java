package com.superh.hz.common.util.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/** 
 * 2015-6-15
 */
public class LoggerConfiguration
{	
	private static Logger log = Logger.getLogger(LoggerConfiguration.class);
	
	/**
	 * 重载log4j配置文件，可使用项目外部配置
	 * @param log4jPath String, log4j文件
	 */
	public static void reLoadLogProperties(String log4jFile){
		PropertyConfigurator.configure(log4jFile);
	}
	
	/**
	 * 设置日志等级
	 * @param level String, 日志等级
	 */
	public static void setLoggerLevel(String level)
	{
		switch (level)
		{
			case "debug" :
				Logger.getRootLogger().setLevel(Level.DEBUG);
				break;
			case "info" :
				Logger.getRootLogger().setLevel(Level.INFO);
				break;
			case "warn" :
				Logger.getRootLogger().setLevel(Level.WARN);
				break;
			case "error" :
				Logger.getRootLogger().setLevel(Level.ERROR);
				break;
			default :
				log.info("the giving log level not match debug or info or warn or error");
				break;
		}
	}
}
