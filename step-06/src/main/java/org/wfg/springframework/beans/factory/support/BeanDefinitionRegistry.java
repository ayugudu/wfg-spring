package org.wfg.springframework.beans.factory.support;

import org.wfg.springframework.beans.factory.config.BeanDefinition;

/**
 * 注册beanDefinition 缓存
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanDefinition);

    String[] getBeanDefinitions();

}
