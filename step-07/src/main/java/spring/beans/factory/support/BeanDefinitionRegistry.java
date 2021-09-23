package spring.beans.factory.support;

import spring.beans.factory.config.BeanDefinition;

/**
 *定义注册的行为
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String  beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();




}
