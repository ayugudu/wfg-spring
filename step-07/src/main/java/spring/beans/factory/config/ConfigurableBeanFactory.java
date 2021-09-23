package spring.beans.factory.config;

import spring.beans.factory.HierarchicalBeanFactory;

/**
 * 配置工厂的加载
 */
public interface ConfigurableBeanFactory extends SingletonBeanRegistry, HierarchicalBeanFactory {
    String SCOPE_SINGLETON="singleton";

    String SCOPE_PROTOTYPE="prototype";

    void addBeanPostProcessors(BeanPostProcessor beanPostProcessor);
    /**
     * 销毁单列对象
     */
    void destroySingletons();
}
