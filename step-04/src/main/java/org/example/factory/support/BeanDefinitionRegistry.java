package org.example.factory.support;

import org.example.factory.config.BeanDefinition;

/**
 * 将beanDefinition 注册进去
 */
public interface BeanDefinitionRegistry {
    public void registerBeanDefinition(String beanName,BeanDefinition beanDefinition);
}
