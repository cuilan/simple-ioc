/**
 * 反射内省包，操作Bean对象
 */
package cn.cuilan.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Bean的工具类
 * 
 * @author cuilan
 */
public class BeanUtils {

	/**
	 * 根据属性名称获得这个属性的set方法
	 * 
	 * @param beanObj
	 *            Bean对象
	 * @param name
	 *            Bean对象对应的属性名称
	 * @return 返回一个Method对象
	 */
	public static Method getWriteMethod(Object beanObj, String name) {
		// 定义要返回的Method对象
		Method method = null;
		// 使用内省技术来实现该方法
		// 1、分析Bean对象，把结果封装到BeanInfo
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(beanObj.getClass());
			// 2、根据BeanInfo获得所有的属性描述器
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			// 3、遍历这些属性描述器
			if (propertyDescriptors != null) {
				for (PropertyDescriptor descriptor : propertyDescriptors) {
					// 判断当前遍历的描述器描述的属性是否是我们要找到的属性
					// 获得当前描述器描述的属性名称
					String pName = descriptor.getName();
					// 使用要找的属性名称与当前描述器描述的属性名称进行比对
					if (pName.equals(name)) {
						// 如果一致，说明找到了，并获得写入属性的set方法
						method = descriptor.getWriteMethod();
					}
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		// 如果没有找到，抛出异常，并提示
		if (method == null) {
			throw new RuntimeException("请检查" + name + "属性的set方法是否创建！");
		}
		// 4、返回找到的set方法
		return method;
	}

}
