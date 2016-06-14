package com.spuerh.hz.bigdata.util.secure;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *  加密工具类
 *  2016-6-8
 */
public class EncryptUtil {
	
	
	/**
	 * @param source源字符串 type加密类型(MD5,SHA)
	 * @return
	 */
	public static String encrypt(String source, String type) {
		StringBuilder sb = new StringBuilder();
	    MessageDigest md5;
	    try {
	      md5 = MessageDigest.getInstance(type);
	      md5.update(source.getBytes());
	      for (byte b : md5.digest()) {
	        sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
	      }
	      return sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	      return null;
	    }
	}
}
