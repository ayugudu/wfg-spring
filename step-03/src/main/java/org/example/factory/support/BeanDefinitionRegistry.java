package org.example.factory.support;

import org.example.factory.config.BeanDefinition;

/**
 * 用于定义客户端注册 beanDefinition
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName , BeanDefinition beanDefinition);
}
