package chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestRunnable {
	
	/**
	 * 使用Runnable方式创建线程任务
	 */
	public static void main(String[] args) {
		
		ExecutorService exec = Executors.newCachedThreadPool();
//		ExecutorService exec = Executors.newFixedThreadPool(3);
//		ExecutorService exec = Executors.newSingleThreadExecutor();//按顺序序列执行
		Future<?>[] f = new Future<?>[3];
		for(int i=0; i<3; i++) {
			//exec.execute(new Printer());
			f[i] = exec.submit(new Printer());
		}
		/*
		 *Parameters:
		 *true的时候会中断task的执行，false时在运行中的task允许正常结束。
		 *
		 *Returns:
		 *如果task不能被取消：false，主要是因为该task已经运行完成；如果task被取消：true。
		 */
		boolean cancled = f[0].cancel(false);
		System.out.println("cancled: " + cancled);
		exec.shutdown();	//结束当前Executor	
	}

}

class Printer implements Runnable {

	private static int count = 0;
	private final int id = count++;
	
	public Printer() {
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<10; i++) {
			System.out.print("#" + id + ":"+i);
		}
	}
	
}
