package test;

import org.junit.Test;

import cn.cuilan.main.BeanFactory;
import cn.cuilan.main.ClassPathXmlApplicationContext;

/**
 * 测试ClassPathXmlApplicationContext
 *
 * @author cuilan
 */
public class TestCPXAC {

	@Test
	public void fun1() {
		BeanFactory bf = new ClassPathXmlApplicationContext("/applicationContext.xml");
		A a1 = (A) bf.getBean("A");
		A a2 = (A) bf.getBean("A");
		A a3 = (A) bf.getBean("A");
		System.out.println(a1.getName() + "\t" + a1.getI());
		System.out.println(a2.getName() + "\t" + a2.getI());
		System.out.println(a3.getName() + "\t" + a3.getI());
	}

	@Test
	public void fun2() {
		BeanFactory bf = new ClassPathXmlApplicationContext("/applicationContext.xml");
		B b1 = (B) bf.getBean("B");
		B b2 = (B) bf.getBean("B");
		B b3 = (B) bf.getBean("B");
		System.out.println(b1.getA().getName());
		System.out.println(b2.getA().getName());
		System.out.println(b3.getA().getName());
	}

	@Test
	public void fun3() {
		BeanFactory bf = new ClassPathXmlApplicationContext("/applicationContext.xml");
		C c1 = (C) bf.getBean("C");
		C c2 = (C) bf.getBean("C");
		C c3 = (C) bf.getBean("C");
		System.out.println(c1.getB().getA().getName());
		System.out.println(c2.getB().getA().getName());
		System.out.println(c3.getB().getA().getName());
	}

}
