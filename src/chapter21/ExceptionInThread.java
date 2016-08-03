package chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExceptionInThread {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		//在这里，加不加try catch都一样会有异常不能捕获

			ExecutorService exec = Executors.newCachedThreadPool();
//			exec.execute(new ExceptionThread());
			Future<?>[] f = new Future<?>[3];
			for(int i=0; i<3; i++) {
				//exec.execute(new Printer());
				f[i] = exec.submit(new CountTask(i));
			}
			
			f[2].cancel(true);
			exec.shutdown();
		
		
	}
}

class ExceptionThread implements Runnable {
	@Override
	public void run() {
		throw new RuntimeException();
	}
}

class CountTask implements Runnable {
	private int id;
	
	public CountTask(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		for(int i= 0 ;i<100;i++){
			System.out.println("#"+id +" printing "+i);
//			Thread.yield();
		}
	}
}