package com.superh.hz.study.javatrain.thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能说明：线程池测试，ScheduledThreadPoolExecutor 
 * 2014-9-3
 */
public class TestScheduledThreadPoolExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
	     exec.scheduleAtFixedRate(new Runnable() {//每隔一段时间就触发异常
	                      @Override
	                      public void run() {
	                           //throw new RuntimeException();
	                           System.out.println("================");
	           }
	     }, 1, 5, TimeUnit.SECONDS);
	     exec.scheduleAtFixedRate(new Runnable() {//每隔一段时间打印系统时间，证明两者是互不影响的
	                      @Override
	                      public void run() {
	                           System.out.println(System.nanoTime());
	                      }
	     }, 1000, 2000, TimeUnit.MILLISECONDS);
	}

}
