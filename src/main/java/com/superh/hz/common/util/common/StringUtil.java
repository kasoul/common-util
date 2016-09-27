package com.superh.hz.common.util.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Description: 字符串处理的公共类
 */
public class StringUtil {

	/**
	 * 用separator将line的分割成String数组，并且每个元素经过trim，然后删除空字符串
	 */
	public static final String[] splitWithoutBlank(String str, String separator) {	
		LinkedList<String> list = new LinkedList<String>();
		if(!isNotBlankString(str)){
			return list.toArray(new String[list.size()]);
		}
		
		String[] lineArray = str.split(separator);
		for(String strele: lineArray){
			String addon = strele.trim();
			if(isNotEmpty(addon)){
				list.add(addon);
			}
		}
		return list.toArray(new String[list.size()]);
	}
	
	/**
	 * 用separator将String的分割成String数组，并且每个元素经过trim
	 */
	public static final String[] splitTrim(String str, String separator) {
		LinkedList<String> list = new LinkedList<String>();
		if(!isNotBlankString(str)){
			return list.toArray(new String[list.size()]);
		}
		String[] lineArray = str.split(separator);
		for(String strele: lineArray){
			list.add(strele.trim());
		}
		return list.toArray(new String[list.size()]);
	}
	
	/**
	 * 用separator将list的所有元素拼接成字符串,并且每个元素经过trim 处理
	 */
	public static <T extends Object> String getJointTrimString(List<T> list, String separator){
		if (list == null || list.size()==0) {
			return "";
		}
		StringBuffer buf = new StringBuffer(256);
		for(int i=0; i<list.size(); i++){
			Object obj = list.get(i);
			if (obj != null) {
				buf.append(obj.toString().trim());
			}
			if(i!=list.size()-1){
				buf.append(separator);
			}
		}
		return buf.toString();
	}
	
	/**
	 * 用separator将String数组的所有元素拼接成字符串,并且每个元素经过trim 处理
	 */
	public static String getJointTrimString(String[] array, String separator){
		if (array == null || array.length==0) {
			return "";
		}
		StringBuffer buf = new StringBuffer(256);
		for(int i=0; i<array.length; i++){
			buf.append(array[i].trim());
			if(i!=array.length-1){
				buf.append(separator);
			}
		}
		return buf.toString();
	}
	
	/**
	 * 用separator将String数组的所有元素拼接成字符串
	 */
	public static String getJointString(String[] array, String separator){
		if (array == null || array.length==0) {
			return "";
		}
		StringBuffer buf = new StringBuffer(256);
		for(int i=0; i<array.length; i++){
			buf.append(array[i]);
			if(i!=array.length-1){
				buf.append(separator);
			}
		}
		return buf.toString();
	}
	
	/**
	 * 用separator将list的所有元素拼接成字符串
	 */
	public static <T extends Object> String getJointString(List<T> list, String separator){
		if (list == null || list.size()==0) {
			return "";
		}
		StringBuffer buf = new StringBuffer(256);
		for(int i=0; i<list.size(); i++){
			Object obj = list.get(i);
			if (obj != null) {
				buf.append(obj.toString());
			}
			if(i!=list.size()-1){
				buf.append(separator);
			}
		}
		return buf.toString();
	}
	
	/**
	 * 用separator将iterator的所有元素拼接成字符串
	 */
	public static <T extends Object> String getJointString(Iterator<T> iterator, String separator) {
		if (iterator == null || !iterator.hasNext()) {
			return "";
		}
		StringBuffer buf = new StringBuffer(256);
		while (iterator.hasNext()) {
			Object obj = iterator.next();
			if (obj != null) {
				buf.append("'"+obj+"'");
			}
			if (iterator.hasNext()) {
				buf.append(separator);
			}
		}
		return buf.toString();
	}
	
	/**
	 * 如果str为null或者空串，返回true
	 */
	public static boolean isEmpty(String str) {
	    return (null == str ||  "".equals(str));
	}
	
	/**
	 * 如果str为不为null并且不为空串，返回true
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null && str.length() > 0);
	}
	
	/**
	 * 如果str为null或者空串或者空白字符串，返回true
	 */
	public static boolean isNotBlankString(String str) {
	    return (str != null && str.trim().length() > 0);
	}
	
	/**
	 * 返回map里的str值，去除开头结尾的空白字符
	 */
	public static  String getStrValueFromMap(Map<String,Object> m , String strKey) {
		Object t = m.get(strKey) ;
		if(t == null )
			return "" ;
		return (m.get(strKey).toString()).trim() ;
	}
	
	/**
	 * String字符串null转空串
	 */
	 public static String getStrValue(String str) {
	    if (str == null)
	        return "";
	    return str;
	 }
	
	/**
	 * String字符串转大写字母
	 */
	public static String toUpperCase(String str) {
		return str == null ? null : str.toUpperCase();
	}

	/**
	 * String字符串转小写字母
	 */
	public static String toLowerCase(String str) {
		return str == null ? null : str.toLowerCase();
	}
	

	public static void main(String args[]){
		String a = ",q ,, a,ss";
		String aa[] = a.split(",");
		//List<String> list = Arrays.asList(aa);
		System.out.println(getJointString(splitWithoutBlank(a, ","),","));
		//System.out.println(aa.length);
		/*for(String s:aa){
			System.out.println(s);
		}*/
	}
}
