package chapter9.package2;

import chapter9.package1.A;

class B implements A{
	
	@Override
	public void print() { //�������public ����ᱨ��
		System.out.println("this is B" + a);
	}

	public static void main(String[] args) {
		B b = new B();
		b.print();
	}
}
