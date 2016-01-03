/**
 * MySpring Ioc 框架实例化Bean包
 */
package cn.cuilan.main;

/**
 * Bean工厂
 * 
 * @author cuilan
 */
public interface BeanFactory {

	/**
	 * 根据Bean的name获取Bean对象的方法
	 * 
	 * @param beanName
	 *            接收一个字符串类型的bean的名称
	 * @return 返回一个Object类型的对象实例
	 */
	public Object getBean(String beanName);

}
