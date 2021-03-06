﻿package com.superh.hz.study.javatrain.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能说明：线程池测试，FixedThreadPool 
 * 2014-9-3
 */
public class TestFixedThreadPool {

	  public static void main(String[] args) {
	        //创建一个可重用固定线程数的线程池
	        ExecutorService pool = Executors.newFixedThreadPool(2);
	        //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
	        Thread t1 = new  HCThreadA("A1");
	        Thread t2 = new  HCThreadA("A2");
	        Thread t3 = new  HCThreadA("A3");
	        Thread t4 = new  HCThreadA("A4");
	        //将线程放入池中进行执行
	        pool.execute(t1);
	        pool.execute(t2);
	        pool.execute(t3);
	        pool.execute(t4);
	        //关闭线程池
	        pool.shutdown();
	}

}
