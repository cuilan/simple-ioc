package cn.cuilan.parser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import cn.cuilan.entity.Bean;
import cn.cuilan.entity.Property;
import org.dom4j.tree.DefaultElement;

/**
 * XML配置文件解析类
 *
 * @author zhang.yan
 * @since 2020/8/16
 */
public class XmlParser {

    /**
     * 获取配置信息
     *
     * @param path 配置文件的路径
     * @return 返回一个Map集合，使用键值对的方式进行存储。
     */
    public static Map<String, Bean> parser(String path) {
        // 创建一个用户返回的Map对象
        Map<String, Bean> map = new HashMap<>();

        // 加载配置文件
        InputStream is = XmlParser.class.getResourceAsStream(path);
        Document document;
        SAXReader reader = new SAXReader();
        try {
            document = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
            throw new RuntimeException("XML配置文件未知的错误！");
        }

        // "//"代表从全文寻找bean的元素
        String xpath = "//bean";
        List<Node> beanList = document.selectNodes(xpath);
        if (beanList != null) {
            for (Node beanNode : beanList) {
                DefaultElement beanElement = (DefaultElement) beanNode;
                Bean bean = new Bean();
                // 将bean的name、class等属性封装到Bean对象中
                String id = beanElement.attributeValue("id");
                String name = beanElement.attributeValue("name");
                String className = beanElement.attributeValue("class");
                String scope = beanElement.attributeValue("scope");

                // 设置bean的属性
                bean.setId(id);
                bean.setName(name);
                bean.setClassName(className);
                // 解析时，需要判断是否有值
                if (scope != null) {
                    bean.setScope(scope);
                }

                // 获取bean元素下的所以的property子元素
                List<Element> propertyList = beanElement.elements("property");
                if (propertyList != null) {
                    for (Element propertyElement : propertyList) {
                        Property property = new Property();
                        // 将属性封装到Property对象中
                        String pName = propertyElement.attributeValue("name");
                        String pValue = propertyElement.attributeValue("value");
                        String pRef = propertyElement.attributeValue("ref");
                        property.setName(pName);
                        property.setValue(pValue);
                        property.setRef(pRef);
                        // 将Property对象封装到Bean对象中
                        bean.getProperties().add(property);
                    }
                }
                // 将Bean对象封装到Map中，返回Map
                map.put(id, bean);
            }
        }
        return map;
    }

}
