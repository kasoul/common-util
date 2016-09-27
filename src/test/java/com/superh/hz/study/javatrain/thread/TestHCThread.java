package com.superh.hz.study.javatrain.thread;


/**
 * 功能说明：线程测试
 * 2014-9-2
 */
public class TestHCThread {
	
	
	public static void main(String[] args) throws InterruptedException{
		
		Thread  b= new HCThreadB();
		b.start();
		 for(int i=0;i<5;i++){
		      Thread a = new HCThreadA("A"+i);
		      a.start();
		}
	}
	
	
	public void Test1(){
		//实验证明junit不能测试多线程，必须在main函数里测试
		HCThreadA a1 = new HCThreadA("A1");
		HCThreadA a2 = new HCThreadA("A2");
		Thread c1 = new Thread(new HCThreadC());
		HCThreadB b = new HCThreadB();
		HCThreadC c = new HCThreadC();
		a1.run();
		a2.run();
	}

}
