package cn.cuilan.beans.factory;

/**
 * BeanFactory
 *
 * @author zhang.yan
 * @since 2020/08-15
 */
public interface BeanFactory {

    /**
     * 获取 FactoryBean 工厂实例的前缀。
     * 例如：名称为 {@code person} 的 FactoryBean，获取 {@code &person} 获得的是一个工厂，
     * 而不是 person 对象实例。
     */
    String FACTORY_BEAN_PREFIX = "&";

    /**
     * 根据 name 获取 Bean 实例
     *
     * @param name bean的名称
     * @return 返回一个Object类型的bean实例
     */
    Object getBean(String name);

}
