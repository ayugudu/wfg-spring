package org.example.beans.factory.support;

import org.example.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegister {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();


}
