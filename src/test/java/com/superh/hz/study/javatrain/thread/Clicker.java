package com.superh.hz.study.javatrain.thread;

/**
 * 功能说明：测试线程优先级
 * 2014-9-9
 * 
 */
public class Clicker implements Runnable  {
	 int click = 0;
	   Thread t;
	   private volatile boolean running = true;
	   public Clicker(int p) {
	       t = new Thread(this);
	       t.setPriority(p);
	   }

	   public void run() {
	       while (running) {
	           click++;
	       }
	   }

	   public void stop() {
	       running = false;
	   }

	   public void start() {
	       t.start();
	   }
}



