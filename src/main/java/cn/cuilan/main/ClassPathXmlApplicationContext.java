/**
 * MySpring Ioc 框架实例化Bean包
 */
package cn.cuilan.main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.cuilan.config.Bean;
import cn.cuilan.config.Property;
import cn.cuilan.config.parse.ConfigManager;
import cn.cuilan.utils.BeanUtils;

/**
 * BeanFactory的实现类
 * 
 * @author cuilan
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

	/*
	 * 希望在ClassPathXmlApplicationContext这个类一创建，就初始化Spring容器（装载Bean实例的容器）。
	 */

	/** 配置信息对象 */
	private Map<String, Bean> config;

	/** 使用一个Map来做Spring的容器，放置Spring所管理的对象 */
	private Map<String, Object> context = new HashMap<>();

	/**
	 * 1、读取配置文件获得需要初始化的Bean的信息<br>
	 * 2、遍历配置初始化Bean<br>
	 * 3、将初始化好的Bean放入容器中
	 * 
	 * @param path
	 *            配置文件的路径
	 */
	public ClassPathXmlApplicationContext(String path) {
		// 1、读取配置文件获得需要初始化的Bean的信息
		config = ConfigManager.getConfig(path);
		// 2、遍历配置初始化Bean
		if (config != null) {
			for (Entry<String, Bean> entry : config.entrySet()) {
				// 获得配置中的beanName信息
				String beanName = entry.getKey();
				// 获得配置中的bean中的信息
				Bean bean = entry.getValue();
				// 为了防止重复创建，先获取一下
				Object existBean = context.get(beanName);
				// 因为createBean方法中也会往context中放入Bean，因此在初始化之前先要判断容器中是否已经存在了这个Bean，然后再去初始化
				// 并且Bean的scope属性值为singleton，才将Bean放入容器中
				if (existBean == null && bean.getScope().equals("singleton")) {
					// 根据bean配置创建Bean对象
					Object beanObj = createBean(bean);
					// 3、将初始化好的Bean放入容器中
					context.put(beanName, beanObj);
				}
			}
		}
	}

	/**
	 * 根据Bean配置来创建Bean实例对象
	 * 
	 * @param bean
	 *            接受的是一个Bean对象
	 * @return 返回一个Object的实例化对象
	 */
	private Object createBean(Bean bean) {
		// 1、获得要创建的Bean的Class
		String className = bean.getClassName();
		// 创建class对象
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("bean元素的class属性配置是否不正确！" + className);
		}
		// 获得class后，需要创建class对应的对象
		Object beanObj = null;
		try {
			beanObj = clazz.newInstance(); // 调用空参构造方法
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("该Bean中没有空参构造器！" + className);
		}
		// 2、获得Bean的属性，将其注入，注入分为两种情况
		if (bean.getProperties() != null) {
			for (Property property : bean.getProperties()) {
				// 不管是哪种方式，都要获取注入的属性名称
				String name = property.getName();

				/* ========================================================== */
				/* 使用Apache BeanUtils工具类 */
				/* ========================================================== */
				String value = property.getValue();
				// 用于注入值类型的属性的，如果有值类型的属性需要注入
				if (value != null) {
					Map<String, String[]> paramMap = new HashMap<>();
					paramMap.put(name, new String[] { value });
					try {
						// 调用BeanUtils中的populate方法将值类型的属性注入（该种注入可以自动完成类型转换）
						org.apache.commons.beanutils.BeanUtils.populate(beanObj, paramMap);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
						throw new RuntimeException("请检查" + name + "属性是否正确！");
					}
				}
				// 用于注入ref的Bean类型的属性的
				if (property.getRef() != null) {
					// 根据属性名称获得注入属性对应的set方法
					Method setMethod = BeanUtils.getWriteMethod(beanObj, name);
					// 要注入其他Bean到当前Bean中，先从context容器中查找当前要注入的Bean是否已经创建，并放入到容器中
					Object existBean = context.get(property.getRef());
					if (existBean == null) {
						// 如果为空，说明容器中还不存在要注入的Bean，将Bean创建，递归调用去指定的创建
						existBean = createBean(config.get(property.getRef()));
						// 如果配置中Bean的scope属性为singleton，才放入容器
						if (config.get(property.getRef()).getScope().equals("singleton")) {
							// 将创建好的Bean放入到context容器中
							context.put(property.getRef(), existBean);
						}
					}
					// 调用set方法注入即可
					try {
						setMethod.invoke(beanObj, existBean);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						throw new RuntimeException("Bean的属性" + name + "没有对应的set方法或方法参数不正确！" + className);
					}
				}
				/* ========================================================== */

				/* ========================================================== */
				/* 自己写的 */
				/* ========================================================== */
				// // 根据属性名称获得注入属性对应的set方法
				// Method setMethod = BeanUtils.getWriteMethod(beanObj, name);
				// // 创建一个需要注入到Bean中的属性，为了统一value和ref
				// Object param = null;
				// // 1、简单，value注入
				// if (property.getValue() != null) {
				// // 获取要注入的属性值
				// String value = property.getValue();
				// param = value;
				// }
				// // 2、麻烦，其他Bean的注入
				// if (property.getRef() != null) {
				// // 要注入其他Bean到当前Bean中，先从context容器中查找当前要注入的Bean是否已经创建，并放入到容器中
				// Object existBean = context.get(property.getRef());
				// if (existBean == null) {
				// // 如果为空，说明容器中还不存在要注入的Bean，将Bean创建，递归调用去指定的创建
				// existBean = createBean(config.get(property.getRef()));
				//
				// // 如果配置中Bean的scope属性为singleton，才放入容器
				// if
				// (config.get(property.getRef()).getScope().equals("singleton"))
				// {
				// // 将创建好的Bean放入到context容器中
				// context.put(property.getRef(), existBean);
				// }
				// }
				// param = existBean;
				// }
				// // 调用set方法注入即可
				// try {
				// setMethod.invoke(beanObj, param);
				// } catch (IllegalAccessException | IllegalArgumentException |
				// InvocationTargetException e) {
				// e.printStackTrace();
				// throw new RuntimeException("Bean的属性" + name +
				// "没有对应的set方法或方法参数不正确！" + className);
				// }
				/* ========================================================== */
			}
		}
		return beanObj;
	}

	/**
	 * 获取Bean对象
	 */
	@Override
	public Object getBean(String beanName) {
		Object bean = context.get(beanName);
		// 如果Bean的scope属性为prototype，那么context中不包含该Bean对象
		if (bean == null) {
			// 如果不存在该Bean对象，那么就创建Bean对象
			bean = createBean(config.get(beanName));
		}
		return bean;
	}

}
