package chapter21;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TestCallable {
	/**
	 * Callable同为接口，具有类型参数的泛型，类型参数表示从call方法中返回的值，
	 * 并且必须用ExecutorService.submit()方法调用它
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 * 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService exec = Executors.newCachedThreadPool();
		
		for(int i=0;i< 10; i++) {
			Future<String> f = exec.submit(new MyCall(i)); //要用ExecutorService对象来提交一个Callable，返回一个Future
			//System.out.println(f.isDone());
			while(!f.isDone()) {
				System.out.println(f.get());//如果不用isDone判定是否完成，get()将会阻塞直至完成
			}
		}
		exec.shutdown();		
	}
	
}

class MyCall implements Callable<String> {

	private int id;
	
	public MyCall(int id) {
		this.id = id;
	}
	
	
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		return "current id is : "+ id;
	}
	
}
