/**
 * 
 */
package chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author eva_shi
 * 生产者与消费者
 *
 */
public class ProducerAndConsumer1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Restaurant r = new Restaurant();
	}

}
class Meal {
	
	private final int orderNum;
	
	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}
	
	public String toString() {
		return "Meal "+ orderNum;
	}
	
} 

class WaitPerson implements Runnable {
	private Restaurant restaurant;
	public WaitPerson(Restaurant r) {
		restaurant = r;
	}
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) {
					while(restaurant.meal == null) {
						this.wait();
					}
				}
				
				System.out.println("got meal and put it to consumers"+ restaurant.chef.count);
				
				synchronized(restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}

class Chef implements Runnable{
	
	private Restaurant restaurant;
	int count = 0;
	
	public Chef(Restaurant r) {
		restaurant = r;
	}
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized(this) { //生产的食物还没有被消费，等待被消费
					while(restaurant.meal != null ){
						wait();
					}
				}
				
				if(++count == 10) {
				//shutdown
					restaurant.exec.shutdownNow();
				}
				System.out.println("cook a meal!"+ count);
				synchronized(restaurant.wp) { //生产食物，并让消费者去消费
					restaurant.meal = new Meal(count);
					restaurant.wp.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
			System.out.println("chef interrupted");
		}
	}
}

class Restaurant {
	
	Meal meal;
	WaitPerson wp = new WaitPerson(this);
	Chef chef = new Chef(this); 
	ExecutorService exec = Executors.newCachedThreadPool();
	
	public Restaurant () {
		exec.execute(wp);
		exec.execute(chef);
	}
	
}