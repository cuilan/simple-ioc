package test;

public class C {

	public C() {
		System.out.println("C对象被创建！");
	}

	private B b;

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

}
