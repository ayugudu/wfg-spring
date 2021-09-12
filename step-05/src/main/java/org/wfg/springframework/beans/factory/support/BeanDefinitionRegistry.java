package org.wfg.springframework.beans.factory.support;

import org.wfg.springframework.beans.factory.config.BeanDefinition;

/**
 * 注册beanDefinition
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册信息
     * @param beanName
     * @param beanDefinition
     */
 void registerBeanDefinition(String beanName , BeanDefinition beanDefinition);

    /**
     * 获取
     * @param beanName
     * @return
     */
 BeanDefinition getBeanDefinition(String beanName);

/**
 * 判断是否包含
 */

boolean containsBeanDefinition(String beanName);

/**
 * 返回注册表中 所有bean名成
 */

String [] getBeanDefinitionNames();
}
