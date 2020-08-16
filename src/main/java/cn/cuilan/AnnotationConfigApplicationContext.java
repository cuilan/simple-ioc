package cn.cuilan;

import cn.cuilan.annotation.Component;
import cn.cuilan.beans.factory.BeanFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhang.yan
 * @date 2020/8/16
 */
public class AnnotationConfigApplicationContext implements BeanFactory {

    private Map<String, Object> beans = new HashMap<>();

    public void scan(String... basePackages) {
        for (String basePackage : basePackages) {
            String rootClassPath = this.getClass().getResource("/").getPath();
            String basePackagePath = basePackage.replace(".", "/");
            List<String> classNames = parseClassNames(rootClassPath, basePackagePath);
            try {
                for (String className : classNames) {
                    Class<?> clazz = Class.forName(className);
                    if (!clazz.isAnnotation()
                            && !clazz.isInterface()
                            && !clazz.isEnum()
                            && clazz.isAnnotationPresent(Component.class)) {
                        Component component = clazz.getAnnotation(Component.class);
                        if ("".equals(component.value())) {
                            String beanName = className.substring(className.lastIndexOf(".") + 1);
                            beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
                            beans.put(beanName, clazz.newInstance());
                        } else {
                            beans.put(component.value(), clazz.newInstance());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> parseClassNames(String rootClassPath, String basePackagePath) {
        List<String> classNames = new ArrayList<>();
        File basePackageDir = new File(rootClassPath + basePackagePath);
        // dir
        if (basePackageDir.isDirectory()) {
            String[] classNamePath = basePackageDir.list();
            if (classNamePath == null) {
                return classNames;
            }
            for (String className : classNamePath) {
                List<String> simpleClassNames = parseClassNames(rootClassPath + basePackagePath + "/", className);
                for (String simpleClassName : simpleClassNames) {
                    classNames.add(basePackagePath.replaceAll("/", ".") + "." + simpleClassName);
                }
            }
        }
        // file
        if (basePackageDir.isFile()) {
            if (!basePackagePath.endsWith(".class")) {
                return classNames;
            }
            basePackagePath = basePackagePath.replaceAll(".class", "");
            classNames.add(basePackagePath);
            return classNames;
        }
        return classNames;
    }

    @Override
    public Object getBean(String name) {
        Object o = beans.get(name);
        if (o == null) {
            throw new RuntimeException("No bean, name: " + name);
        }
        return o;
    }
}
