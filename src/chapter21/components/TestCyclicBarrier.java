/**
 * 
 */
package chapter21.components;

import java.util.concurrent.CyclicBarrier;

/**
 * @author eva_shi
 * CyclicBarrier 
 */
public class TestCyclicBarrier {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Horse implements Runnable {
	
	private static int count = 0;
	private final int id = count++;
	private CyclicBarrier barrier;
	
	public Horse (CyclicBarrier barrier) {
		this.barrier = barrier;
	}
	
	public void run() {
		while(!Thread.interrupted()) {
			
		}
	}
}