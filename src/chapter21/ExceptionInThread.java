package chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionInThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//在这里，加不加try catch都一样会有异常不能捕获
		try {
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ExceptionThread());
		} catch (RuntimeException ex) {
//			ex.printStackTrace();
			System.out.println("exception is handled");
		}
		
	}
}

class ExceptionThread implements Runnable {
	@Override
	public void run() {
		throw new RuntimeException();
	}
}