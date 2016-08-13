/**
 * 
 */
package chapter21.components;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author eva_shi
 * 
 *         Java SE5的java.util.concurrent 中引入的构件。
 *         两个需要同步的任务需要共享一个CountDownLatch，调用await的时候只有CountDownLatch的值为0
 *         才能将调用await的线程唤醒。
 *         WaitingTask等待CountDownLatch的值到0的时候才会去执行。所有的TaskPort执行完, latch
 *         count值为0，这时WaitingTask才能被调度执行。
 */
public class TestCountDownLatch {

	public static void main(String[] args) {

		ExecutorService exec = Executors.newCachedThreadPool();
		CountDownLatch c = new CountDownLatch(30);

		for (int i = 0; i < 10; i++) {
			exec.execute(new WaitingTask(c));
		}

		for (int i = 0; i < 30; i++) {
			exec.execute(new TaskPortion(c));
		}

		System.out.println("launch all tasks");
		exec.shutdown();
	}

}

class TaskPortion implements Runnable {

	private static int count = 0;
	private final int id = count++;

	private final CountDownLatch latch;

	public TaskPortion(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {
		try {
			doWork();
			latch.countDown();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void doWork() throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(20);
		System.out.println(this + "completed " + latch.getCount());
	}

	public String toString() {
		return String.format("%1$-3d ", id);
	}
}

class WaitingTask implements Runnable {

	private static int count = 0;
	private final int id = count++;
	private final CountDownLatch latch;

	public WaitingTask(CountDownLatch latch) {
		this.latch = latch;
	}

	public void run() {
		try {
			latch.await();// 当latch减少到0的时候wait才会取消
			System.out.println("latchbarrier is passed for: " + this + " "
					+ latch.getCount());
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public String toString() {
		return String.format("Waiting task %1$-3d ", id);
	}
}