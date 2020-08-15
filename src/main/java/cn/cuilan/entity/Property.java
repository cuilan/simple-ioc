package cn.cuilan.entity;

/**
 * property节点对象，包含name、value、ref三个属性。
 *
 * @author zhang.yan
 * @date 2020/8/16
 */
public class Property {

    /**
     * property节点中的name属性
     */
    private String name;

    /**
     * property节点中的value属性
     */
    private String value;

    /**
     * property节点中的ref属性
     */
    private String ref;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

}
