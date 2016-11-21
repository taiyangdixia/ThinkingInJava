package chapter2;

import java.util.HashSet;

public class VariableType {

	
	static int a;
	static {
		System.out.println("this is a static block");
	}
	
	//static Haha haha =  new Haha(); 编译错
	
	class Haha{
		static final int a=1;
		public Haha() {
			System.out.println("haha");
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int b=92;
		print(b);
		
	}
	
	public static void print(double b){
		System.out.println(b);
	}

} 
