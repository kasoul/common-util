package com.spuerh.hz.bigdata.util.common;

import java.util.Comparator;
import java.util.List;

/**
 * 功能说明：排序算法，对List<T>
 * 成员属性：
 * 成员方法：
 * 	void bubbleSort(List<T> list)，冒泡排序
 *  void selectSort(List<T> list)，选择排序
 *  void quickSort(List<T> list)，快速排序
 *  void heapSort(List<T> list),堆排序
 * 2014-08-20
 * @update   
 */

public class MySortUtil{

	static MyComparatorGenerics comparator = new MyComparatorGenerics();
	
	/** 
     * 冒泡排序
     * @param List<T> list，Comparator<T> comparator
     */ 
	public static <T> void bubbleSort(List<T> list,Comparator<T> comparator){
		int maxindex = list.size()-1;
		for(int i=0;i<maxindex;i++)   
		//外循序，循环size-1次，循环一次后一个最大值移到表尾
			for(int j=0;j<maxindex-i;j++){  
			//内循环，每次i的值进来，再比较size-1-i次，从0位置开始依次与后一个元素比较
				T temp=list.get(j);  //保存j位置元素
				T next=list.get(j+1);  //保存j位置后一个元素
				if(comparator.compare(temp, next)>0){
				//如果当前元素大于后一个，则交换位置，即较大元素往后移动
				list.set(j, next);
				list.set(j+1, temp);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void bubbleSort(List<T> list){
		bubbleSort(list,comparator);
	}
	
	/** 
     * 选择排序
     * @param List<T> list，Comparator<T> comparator
     */ 
	public static <T> void selectSort(List<T> list,Comparator<T> comparator){
		int size = list.size();
		for(int i=0;i<size-1;i++)
		//外循序，循环size-1次，循环一次后一个最小值移到表头
			for(int j=i+1;j<size;j++){
				//内循环，依次比较i位置元素和后面所有元素
				T temp=list.get(i);
				T next=list.get(j);
				if(comparator.compare(temp, next)>0){
					//如果i位置大于j，则调换，即把最小元素放到i
					list.set(i, next);
					list.set(j, temp);
			}
		}
	}
	
	public static <T> void selectSort(List<T> list){
		selectSort(list,comparator);
	}
	
	/** 
     * 快速排序
     * @param List<T> list，Comparator<T> comparator
     */ 
	public static <T> void quickSort(List<T> list,Comparator<T> comparator){
		quickSort(list,0,list.size()-1,comparator);
	}
	
	public static <T> void quickSort(List<T> list){
		quickSort(list,0,list.size()-1,comparator);
	}
	
	/** 
     * 快速排序,带边界下标
     * @param List<T> list，int start,int end，Comparator<T> comparator
     */ 
	private static <T> void quickSort(List<T> list,int start,int end,Comparator<T> comparator){
	  	if(start<end) //递归出口，中心点不能到左右边界
	       { 
	           T key=list.get(start);  //初始化保存基元  
	           int i=start;
	           int j;  //初始化i,j  
	           for(j=start+1;j<=end;j++) {
	          //如果此处元素小于基元，则把此元素和i+1处元素交换，并将i加1，如大于或等于基元则继续循环  
	               if(comparator.compare(list.get(j),key)<0)
	               { 
	                   T temp=list.get(j); 
	                   list.set(j,list.get(i+1)); 
	                   list.set(i+1,temp); 
	                   i++; 
	               } 
	           } 
	           list.set(start,list.get(i));  //交换i处元素和基元  
	           list.set(i, key);   //至此i处左边的元素都小于i处元素，右边的元素都大于i处
	           quickSort(list, start, i-1,comparator);  //递归调用，对左边快排
	           quickSort(list, i+1, end,comparator);    //递归调用，对右边快排
	       }     
	} 
	
	/** 
     * 堆排序
     * @param List<T> list，Comparator<T> comparator
     */ 
	private static <T> void heapSort(List<T> list,Comparator<T> comparator){
		int heapsize = list.size();       //集合中元素的个数
        for(int i=heapsize/2-1;i>=0;i--){
        	 heapify(list,i,heapsize,comparator);  
        	 //从下往上建队才行的通，从上开始建会导致上层丢失，下层重复访问
            }
        int i=0;
        for(i=heapsize-1;i>=1;i--){
            T temp = list.get(0);  
            list.set(0,list.get(i));
            list.set(i,temp);  //将最大节点的内容与移至尾部
            heapify(list,0,i,comparator);  //继续在剩下的元素里建堆
            }
	}
	
	public static <T> void heapSort(List<T> list){
		heapSort(list,comparator);
	}
	
	/** 
     * 堆排序：构建堆，让根节点成为最大节点
     * 堆的核心思想是：先序遍历转化为二叉树
     * @param List<T> list，Comparator<T> comparator
     */ 
	private static <T> void heapify(List<T> list, int father, int heapsize,Comparator<T> comparator) {
		int leftchild = 2 * father + 1;  // 左孩子的下标（如果存在的话）
        int rightchild = 2 * father + 2; // 右孩子的下标（如果存在的话），
        int largestindex = 0;  //寻找3个节点中最大值节点的下标
        if(leftchild<heapsize && (comparator.compare(list.get(leftchild),list.get(father))>0))
        	largestindex = leftchild;  //如果左孩子存在并且大于父节点，leftchild最大
        else
        	largestindex = father;
        if(rightchild<heapsize && (comparator.compare(list.get(rightchild),list.get(largestindex))>0))
        	largestindex = rightchild;  //如果右孩子存在并且大于最大index，rightchild最大
        if(largestindex!=father){
            T temp = list.get(father);  //将最大节点的内容与父节点对换
            list.set(father,list.get(largestindex));
            list.set(largestindex,temp);
            heapify(list,largestindex,heapsize,comparator); //如果父节点发对换，那子节点也必须再进行       
        }
	} 
}
