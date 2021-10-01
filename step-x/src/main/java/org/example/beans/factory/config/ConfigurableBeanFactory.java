package org.example.beans.factory.config;


import org.example.beans.factory.HierarchicalBeanFactory;

public interface ConfigurableBeanFactory extends SingletonBeanRegistry, HierarchicalBeanFactory {
    String SCOPE_SINGLETON ="singleton";

    String SCOPE_PROTOTYPE="prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

}
