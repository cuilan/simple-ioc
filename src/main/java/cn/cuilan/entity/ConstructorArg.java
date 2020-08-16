package cn.cuilan.entity;

/**
 * 构造注入参数
 *
 * @author zhang.yan
 * @date 2020/8/16
 */
public class ConstructorArg {

    private String name;

    private String value;

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
