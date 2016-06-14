package com.spuerh.hz.bigdata.util.common;

import java.util.Arrays;
import java.util.List;

/**
 *  简单的计算工具
 *  2015-1-24
 */
public class MathTool {
	
	public static double sum(List<Double> numbers){
		double sum = 0.0;
		for(double number: numbers){
			sum += number;
		}
		return sum;
	}
	
	public static double avrage(List<Double> numbers){
		if(numbers.size()==0||numbers==null){
			return 0;
		}
		return sum(numbers)/numbers.size();
	}

	public static double variance(List<Double> numbers){
		if(numbers.size()==0||numbers==null){
			return 0;
		}
		double avrage = avrage(numbers);
		double sum = 0.0;
		for(double number: numbers){
			sum += (number-avrage)*(number-avrage);
		}
		return sum/numbers.size();
	}
	
	public static void main(String[] args){
		List<Double> numbers = Arrays.asList(1.0,2.0,3.0,4.0,1.5,2.5,3.5,14.5);
		System.out.println(sum(numbers));
		System.out.println(avrage(numbers));
		System.out.println(variance(numbers));
	}
}
