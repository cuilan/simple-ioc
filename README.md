# My Spring IoC 框架
### 一、依赖
* commons-beanutils 依赖包 `commons-beanutils-1.9.2.jar`
* commons-logging 依赖包`commons-logging-1.2.jar`
* dom4j 依赖包 `dom4j-1.6.1.jar`
* jaxen 依赖包 `jaxen-1.1-beta-4.jar`

### 二、使用
* 将 `commons-beanutils-1.9.2.jar`、`commons-logging-1.2.jar`、`dom4j-1.6.1.jar`、`jaxen-1.1-beta-4.jar` 导入到lib目录下。
* 在 src 目录下创建 `*.xml` 文件（建议命名为：`applicationCntext.xml` 或 `beans.xml`），配置如：

```xml
    <?xml version="1.0" encoding="UTF-8"?>
	<beans>
		<!-- name、class、scope(默认为singleton单例模式) -->
		<bean name="user" class="package.ClassName" scope="singleton">
			<property name="name" value="cuilan"></property>
			<!-- 自动类型转换 -->
			<property name="age" value="123" />
			......
		</bean>
		<!-- prototype非单例模式 -->
		<bean name="userDao" class="package.ClassNameDao" scope="prototype">
			<property name="existUser" ref="user" />
		</bean>
		......
	</beans>
```

* 启动，如：

```java
	import cn.cuilan.main.BeanFactory;
	import cn.cuilan.main.ClassPathXmlApplicationContext;
	
	public class Test {
		public static void main(String[] args) {
			BeanFactory beanContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
			User user = (User) beanContext.getBean("user");
			UserDao userDao = (UserDao) beanContext.getBean("userDao");
			// ......
		}
	}
```

### 三、设计原理

#### 1、解析XML配置文件

1、创建解析器。

2、加载配置文件，获得document对象。

3、定义xpath表达式，取出所以的bean元素。

4、对bean元素进行遍历，将bean的name、class等属性封装到Bean对象中。

5、获取bean元素下的所以的property子元素，将属性封装到Property对象中。

6、将Property对象封装到Bean对象中。

7、将Bean对象封装到Map中，并返回。

#### 2、根据配置文件初始化容器

详见doc目录Java API...

#### 3、根据配置文件讲Bean创建病放置到容器中

详见doc目录Java API...
