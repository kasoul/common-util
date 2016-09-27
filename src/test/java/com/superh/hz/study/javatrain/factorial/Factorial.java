package com.superh.hz.study.javatrain.factorial;

/**
 * 功能说明：处理阶乘
 * 2014-8-22
 */
public class Factorial {

	public static long sum(int n){
			long s = 1;
			long sum = 0;
			for(int i=1;i<=n+1;i++){
				sum = s+sum;
				s = s*i;
			}
			return sum-1;
	}
	
	public static void main(String[] args) {
		System.out.println(Factorial.sum(20));
	}
	

}
