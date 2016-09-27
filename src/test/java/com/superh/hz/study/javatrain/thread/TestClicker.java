package com.superh.hz.study.javatrain.thread;

/**
 * 功能说明：测试线程优先级
 * 2014-9-9
 */
public class TestClicker {
		   public static void main(String args[]) {
		       Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		       Clicker hi = new Clicker(Thread.NORM_PRIORITY + 2);
		       Clicker lo = new Clicker(Thread.NORM_PRIORITY - 2);
		       lo.start();
		       hi.start();
		       try {
		           Thread.sleep(10000);
		       } catch (InterruptedException e) {
		           System.out.println("Main thread interrupted.");
		       }
		       lo.stop();
		       hi.stop();
		       // Wait for child threads to terminate.
		       try {
		           hi.t.join();
		           lo.t.join();
		       } catch (InterruptedException e) {
		           System.out.println("InterruptedException caught");
		       }

		       System.out.println("Low-priority thread: " + lo.click);
		       System.out.println("High-priority thread: " + hi.click);
		   }

}
