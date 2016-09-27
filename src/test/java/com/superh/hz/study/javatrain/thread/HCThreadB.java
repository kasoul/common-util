package com.superh.hz.study.javatrain.thread;

/**
 * 功能说明：线程实例B
 * 2014-9-2
 * 
 */
public class HCThreadB extends Thread{
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println("!!!!!线程对象B运行"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
