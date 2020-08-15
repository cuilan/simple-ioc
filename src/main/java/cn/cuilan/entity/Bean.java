package cn.cuilan.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * applicationContext.xml配置文件中的Bean对象
 *
 * @author zhang.yan
 * @date 2020/8/16
 */
public class Bean {

    /**
     * bean节点元素的id属性
     */
    private String id;

    /**
     * bean节点元素的name属性
     */
    private String name;

    /**
     * bean节点元素的class属性
     */
    private String className;

    /**
     * bean节点元素的scope属性，默认值为singleton
     */
    private String scope = "singleton";

    /**
     * bean节点元素的property属性
     */
    private List<Property> properties = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

}
