package chapter9.package2;

import chapter9.package1.A;

class B implements A{
	
	@Override
	public void print() { //必须添加public 否则会报错
		System.out.println("this is B" + a);
	}
}
