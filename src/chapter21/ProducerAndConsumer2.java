/**
 * 
 */
package chapter21;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author eva_shi
 * 生产者-消费者 BlockingQueue 同步队列
 * 
 * BlockingQueue队列自身有同步机制，当其内没有数据时会等待数据的加入，有数据时会执行。
 * LinkedBlockingQueue 不限长同步队列
 * ArrayBlockingQueue (k) 固定长度同步队列
 * SynchronousQueue 大小为1的同步队列
 */
public class ProducerAndConsumer2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockingQueue<LiftOff> queue = new LinkedBlockingQueue<LiftOff>();
		LiftOffRunner runner = new LiftOffRunner(queue);
		Thread t = new Thread(runner);
		t.start();
		
		for(int i= 0;i<5; i++) {
			queue.add(new LiftOff(5));
		}
		
		t.interrupt();
	}

}

class LiftOffRunner implements Runnable {
	
	private BlockingQueue<LiftOff> rockets;
	
	public LiftOffRunner(BlockingQueue<LiftOff> queue) {
		rockets = queue;
	}
	
	public void add(LiftOff o) {
		rockets.add(o);
	}
	
	public void run() {
		try {
			
			while(!Thread.interrupted()) {
				
				LiftOff rocket = rockets.take();
				rocket.run();
			
			} 
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("exit liftOffRunner run");
	}
}

/**
 * 发射火箭之前的倒计时
 */
class LiftOff implements Runnable {
	protected int countDown = 10;
	private int taskCount = 0;
	private final int id = taskCount++;
	
	public LiftOff(){}
	
	public LiftOff(int countDown) {
		this.countDown = countDown;
	}
	
	public String status() {
		return "#"+ id +"(" + (countDown>0?countDown: "LiftOff")+")";
	}
	
	public void run(){
		while(countDown-->0) {
			System.out.println(status());
			Thread.yield();
		}
	}
}
