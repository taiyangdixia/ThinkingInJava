/**
 * 
 */
package chapter21;

import java.util.Date;

/**
 * @author eva_shi
 *
 */
public class Volatile {

	private volatile int a=1;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Volatile v = new Volatile();
		v.a++;
		System.out.println(v.a);
		String s ="()";
		
		System.out.println(s.replace("(", ":"));
		System.out.println(new Date().toLocaleString());
	}

}
