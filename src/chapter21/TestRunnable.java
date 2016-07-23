package chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestRunnable {
	
	/**
	 * 使用Runnable方式创建线程任务
	 */
	public static void main(String[] args) {
		
//		ExecutorService exec = Executors.newCachedThreadPool();
		ExecutorService exec = Executors.newFixedThreadPool(3);
//		ExecutorService exec = Executors.newSingleThreadExecutor();//按顺序序列执行
		for(int i=0; i<3; i++) {
			exec.execute(new Printer());
		}
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
			System.out.print("#" + id + ":"+i+" ");
		}
	}
	
}
