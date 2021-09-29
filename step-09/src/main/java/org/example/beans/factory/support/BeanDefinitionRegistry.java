package org.example.beans.factory.support;

import org.example.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    /**
     *  向注册表中注册
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 使用 bean 名称获取beanDefinition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();
}
