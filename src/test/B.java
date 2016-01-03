package test;

public class B {

	public B() {
		System.out.println("B对象被创建！");
	}

	private A a;

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public static void main(String[] args) {

	}

}
