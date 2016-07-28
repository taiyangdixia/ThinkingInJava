package chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CaptureUncaughtException {

	/**
	 * 对比程序ExceptionInThread
	 * 实现ThreadFactory方法，然后利用Thread.UncaughtExceptionHandler来捕获异常，可以让程序正常结束。
	 * 将实现的ThreadFactory对象传入Executors中，生成ExecutorService
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService exec = Executors
				.newCachedThreadPool(new HandlerThreadFactory());
		exec.execute(new ExceptionThread2());

	}
	/**
	 * 创建default的未捕获异常处理器,Thread.setDefaultUncaughtExceptionHandler。
	 * 如果线程有专有的未捕获异常处理器，则默认的处理器不会被调用
	 */
/*	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ExceptionThread2());
	}
*/
}

/**
 * 创建一个线程，线程中会获取UncaughtExceptionHandler 并且跑出异常
 * 
 * @author eva_shi
 * 
 */
class ExceptionThread2 implements Runnable {

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println("ex" + t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}

}

/**
 * UncaughtExceptionHandler类实现
 * 
 * @author eva_shi
 * 
 */
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		System.out.println("caught " + e);
	}

}

/**
 * 实现ThreadFactory
 * 
 * @author eva_shi
 * 
 */
class HandlerThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		System.out.println("creating new thread");
		Thread t = new Thread(r);
		System.out.println("created new thread");
		t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		System.out.println("eh = " + t.getUncaughtExceptionHandler());
		return t;
	}

}