package com.spuerh.hz.common.util.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/** 
 * 2015-6-15
 */
public class LoggerConfiguration
{	
	private static Logger log = Logger.getLogger(LoggerConfiguration.class);
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
