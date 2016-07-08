package com.spuerh.hz.common.util.common;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * 功能说明：自定义Comparator，比较所有类型，带泛型
 * 成员属性
 * 成员方法：
 *  int compare2(Object obj0, Object obj1)
 *  int compare(Object obj0, Object obj1)
 *  int compare3(Object obj0, Object obj1)
 *  int compareString(String str0,String str1)
 *  boolean isInterface(Object o , String szInterface)
 * 2014-08-20
 * @update   
 */
@SuppressWarnings("rawtypes")
public class MyComparatorGenerics<T extends Object> implements Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {
		@SuppressWarnings("unchecked")
		T obj0 = (T)arg0;
		@SuppressWarnings("unchecked")
		T obj1 = (T)arg1;
		if((obj0 instanceof String)&&(obj1 instanceof String)){
			String value0 = (String)obj0;
			String value1 = (String)obj1;
			int i = compareString(value0,value1);
			return i;
		}else if((obj0 instanceof Integer)&&(obj1 instanceof Integer)){
			Integer value0 = (Integer)obj0;
			Integer value1 = (Integer)obj1;
			int v0 = value0.intValue();
			int v1 = value1.intValue();
			if(v0>v1)return 1;
			else if(v0<v1)return -1;
			else return 0;
		}else if((obj0 instanceof Long)&&(obj1 instanceof Long)){
			Long value0 = (Long)obj0;
			Long value1 = (Long)obj1;
			long v0 = value0.longValue();
			long v1 = value1.longValue();
			if(v0>v1)return 1;
			else if(v0<v1)return -1;
			else return 0;
		}else if((obj0 instanceof Float)&&(obj1 instanceof Float)){
			Float value0 = (Float)obj0;
			Float value1 = (Float)obj1;
			float v0 = value0.floatValue();
			float v1 = value1.floatValue();
			if(v0>v1)return 1;
			else if(v0<v1)return -1;
			else return 0;
		}else if((obj0 instanceof Double)&&(obj1 instanceof Double)){
			Double value0 = (Double)obj0;
			Double value1 = (Double)obj1;
			double v0 = value0.doubleValue();
			double v1 = value1.doubleValue();
			if(v0>v1)return 1;
			else if(v0<v1)return -1;
			else return 0;
		}else if((obj0 instanceof Short)&&(obj1 instanceof Short)){
			Short value0 = (Short)obj0;
			Short value1 = (Short)obj1;
			short v0 = value0.shortValue();
			short v1 = value1.shortValue();
			if(v0>v1)return 1;
			else if(v0<v1)return -1;
			else return 0;
		}else if((obj0 instanceof Character)&&(obj1 instanceof Character)){
			Character value0 = (Character)obj0;
			Character value1 = (Character)obj1;
			char v0 = value0.charValue();
			char v1 = value1.charValue();
			if(v0>v1)return 1;
			else if(v0<v1)return -1;
			else return 0;
		}else if((obj0 instanceof Boolean)&&(obj1 instanceof Boolean)){
			Boolean value0 = (Boolean)obj0;
			Boolean value1 = (Boolean)obj1;
			boolean v0 = value0.booleanValue();
			boolean v1 = value1.booleanValue();
			if(v0&&!v1)return 1;
			else if(!v0&&v1)return -1;
			else return 0;
		}
		else{
			String obj0Strig=obj0.toString();
			String obj1Strig=obj1.toString();
			int i = compareString(obj0Strig,obj1Strig);
			return i;
		}
	}
	
	/** 
     * 比较两个对象的大小，使用TreeSet
     * @Override 重写接口的compare方法
     * @param obj0，对象0，obj1，对象1
     * @return int, 1(obj0大于obj1),0(obj0等于obj1)，-1(obj0小于obj1)
     */ 
	public int compare2(Object arg0, Object arg1) {
		@SuppressWarnings("unchecked")
		T obj0 = (T)arg0;
		@SuppressWarnings("unchecked")
		T obj1 = (T)arg1;
		if(((obj0 instanceof String)&&(obj1 instanceof String)) ||
		   ((obj0 instanceof Integer)&&(obj1 instanceof Integer)) ||
		   ((obj0 instanceof Long)&&(obj1 instanceof Long)) ||
		   ((obj0 instanceof Float)&&(obj1 instanceof Float)) ||
		   ((obj0 instanceof Double)&&(obj1 instanceof Double)) ||
		   ((obj0 instanceof Character)&&(obj1 instanceof Character)) ||
		   ((obj0 instanceof Short)&&(obj1 instanceof Short)) ||
		   ((obj0 instanceof Boolean)&&(obj1 instanceof Boolean))
		   ){
			if(obj0.equals(obj1)){
			return 0;
		   }else {
			   TreeSet<T> ts= new TreeSet<T>();
			   ts.add(obj0);
			   ts.add(obj1);
			   if(ts.first()==obj0)
			   return -1;
			   else
			   return 1;
		   }
		}
		else{
			String obj0Strig=obj0.toString();
			String obj1Strig=obj1.toString();
			if(obj0Strig.equals(obj1Strig)){
				return 0;
			   }else {
				   TreeSet<String> ts= new TreeSet<String>();
				   ts.add(obj0Strig);
				   ts.add(obj1Strig);
				   if(ts.first()==obj0Strig)
				   return -1;
				   else
				   return 1;
			   }
		}
	}

	/** 
     * 比较两个对象的大小，分为Comparable实现和非Comparable实现
     * @param obj0，对象0，obj1，对象1
     * @return int, 1(obj0大于obj1),0(obj0等于obj1)，-1(obj0小于obj1)
     */ 
	public int compare3(Object obj0, Object obj1) {
	   //if(isInterface(obj0,"Comparable")&&isInterface(obj1,"Comparable")){//传入String，报异常StackOverflowError
	   if((obj0 instanceof Comparable)&&(obj1 instanceof Comparable)){//用该无法比较成功，返回都是零
			Comparable<T> value0 = (Comparable<T>)obj0;
			Comparable<T> value1 = (Comparable<T>)obj1;
			if(value0.compareTo((T) value1)>0) return 1;
			else if(value0.compareTo((T) value1)<0) return -1;
			else return 0;
		}
		else{
			String obj0Strig=obj0.toString();
			String obj1Strig=obj1.toString();
			int i = compareString(obj0Strig,obj1Strig);
			return i;
		}
	}
	
	public int compareString(String str0,String str1){
		str0=(str0==null?"":str0);
		str1=(str1==null?"":str1);
		if(str0.equals("") && (!str1.equals(""))){
			return -1;
		}else if(str1.equals("") && (!str0.equals(""))){
			return 1;
		}else if(str0.equals(str1)){
			return 0;
		}
		else{
			int k=0;
			for(int i=0;i!=str0.length()&&(i!=str1.length());i++){
				if(str0.charAt(i)>str1.charAt(i)){
					k=1;break;
				}else if(str1.charAt(i)>str0.charAt(i)){
					k=-1;break;
				}else if(str0.charAt(i+1)=='\0'&&(str1.charAt(i)!='\0')){
					k=-1;break;
				}else if(str1.charAt(i+1)=='\0'&&(str0.charAt(i)!='\0')){
					k=1;break;
				}else{
					continue;
				}
			}
			return k;
		}
	}
	
	/** 
     * 判断对象o是否实现指定接口，或父类是否实现指定接口，无需直接实现，实现指定接口的子接口也可以
     * @param Object o，对象，String szInterface，指定接口名
     * @return boolean
     */ 
	public boolean isInterface(Object o , String szInterface)
    {
		    Class c = o.getClass();
            Class[] face = c.getInterfaces();
            for (int i = 0, j = face.length; i < j; i++) 
            {
                    if(face[i].getName().equals(szInterface))
                    {
                            return true;
                    }
                    else
                    { 
                            Class[] face1 = face[i].getInterfaces();
                            for(int x = 0; x < face1.length; x++)
                            {
                                    if(face1[x].getName().equals(szInterface))
                                    {
                                            return true;
                                    }
                                    else if(isInterface(face1[x], szInterface))
                                    {
                                            return true;
                                    }
                            }
                    }
            }
            if (null != c.getSuperclass())
            {
                    return isInterface(c.getSuperclass(), szInterface);
            }
            return false;
    }

}
