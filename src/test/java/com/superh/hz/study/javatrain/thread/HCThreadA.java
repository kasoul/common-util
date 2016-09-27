package com.superh.hz.study.javatrain.thread;

/**
 * 功能说明：线程例子A
 * 2014-9-2
 * 
 */
public class HCThreadA extends Thread{
	
	
	private String name;
	
	public HCThreadA(String name){
		this.name=name;	
	}	

	@Override
	public void run() {
		for(int i=1;i<=10;i++){
			System.out.println(this.currentThread().getName()+"=====线程对象"+name+"运行"+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
