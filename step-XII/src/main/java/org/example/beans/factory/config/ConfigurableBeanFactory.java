package org.example.beans.factory.config;

import org.example.beans.factory.HierarchicalBeanFactory;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON="singleton";

    String SCOPE_PROTOTYPE="prototype";


    void addBeanPostProcessor(BeanPostProcessor beanPostProcess);

    void destroySingletons();

}
