package org.example.beans.factory.support;

import org.example.beans.factory.config.BeanDefinition;

/**
 * beanDefinition 注册
 */
public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册 BeanDefinition
     *
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 使用bean名称查询BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 判断是否包含指定名称的beanDefinition
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回注册表中所有bean名称
     */

    String[] getBeanDefinitionNames();

}
