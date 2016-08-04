package chapter21;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author eva_shi
 * PipedReader
 * PiperWriter
 * 生产者-消费者 模式
 * 可以看做是用了同步队列
 * 很好，简便
 */
public class PipeLine {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Sender send = new Sender();
		Receiver rec = new Receiver(send);
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(send);
		exec.execute(rec);
//		exec.shutdown();
		TimeUnit.MILLISECONDS.sleep(10000);
		exec.shutdownNow();
	}

}

class Sender implements Runnable {
	
	private Random rand = new Random(47);
	private PipedWriter out = new PipedWriter();
	
	public PipedWriter getPipedWriter() {
		return out;
	}
	
	public void run() {
		try {
			while(true) {
				for(char c='A'; c<='z';c++) {
					out.write(c);
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class Receiver implements Runnable {
	
	private PipedReader in;
	
	public Receiver(Sender sender) throws IOException {
		in = new PipedReader(sender.getPipedWriter());
	}
	
	public void run() {
		try {
			while(true) {
				System.out.println("received => " + (char)in.read());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
