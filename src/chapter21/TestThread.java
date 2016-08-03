package chapter21;

public class TestThread {
	public static void main(String[] args) {
		new Thread() {
			public void run(){
				System.out.println("haha");
			}
		};
	}
}
