package org.wfg.springframework.beans.factory;

import org.wfg.springframework.beans.BeansException;
import org.wfg.springframework.beans.factory.config.BeanDefinition;
import org.wfg.springframework.beans.factory.config.BeanPostProcessor;

/**
 *  bean工厂要实现的配置接口，提供bean definition的解析,注册功能,
 */
public interface ConfigurableListableBeanFactory extends  ListableBeanFactory{

BeanDefinition getBeanDefinition(String beanName) throws  Exception;

void preInstantiateSingletons() throws BeansException;

 void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


}
