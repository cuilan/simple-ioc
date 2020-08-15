# simple-ioc
### 一、依赖
* `commons-beanutils`
* `commons-logging`
* `dom4j`
* `jaxen`

### 二、使用

* 在 src 目录下创建 `*.xml` 文件（`applicationCntext.xml` 或 `beans.xml`），配置如：

```xml
    <?xml version="1.0" encoding="UTF-8"?>
	<beans>
		<!-- name、class、scope(默认为singleton单例模式) -->
		<bean name="user" class="package.ClassName" scope="singleton">
			<property name="name" value="cuilan"/>
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
	import cn.cuilan.beans.factory.BeanFactory;
	import cn.cuilan.ClassPathXmlApplicationContext;
	
	public class Test {
		public static void main(String[] args) {
			BeanFactory beanContext = new ClassPathXmlApplicationContext("/applicationContext.xml");
			User user = (User) beanContext.getBean("user");
			UserDao userDao = (UserDao) beanContext.getBean("userDao");
			// ......
		}
	}
```
