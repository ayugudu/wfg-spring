package org.example.factory.support;

import org.example.factory.config.BeanDefinition;

/**
 * 提供将 保存类型信息的 类给注册进去
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册的信息放到 map中 ，所以需要name
     * @param name
     * @param beanDefinition
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
