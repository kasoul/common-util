package com.superh.hz.util.dbtool;

import java.io.IOException;
import java.nio.charset.Charset;

/** 
 * @Project: big-apple-util
 * @File: TestCharSet.java 
 * @Date: 2016年2月16日 
 * @Author: 黄超（huangchaohz）
 */

/**
 *  @Describe:
 */
public class TestCharSet {
	public static void main(String[] args) throws IOException {
		test();
		System.out.println(new TestCharSet().getClass().getProtectionDomain());
	}
	
	
	public static void test(){
		System.out.println(Charset.defaultCharset());
	}
}
