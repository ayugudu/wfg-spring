package org.example.factory.support;

import org.example.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 定义实力化策略
 */
public interface InstantiationStrategy {

 Object instantiate(BeanDefinition beanDefinition, String beanName,Constructor constructor,Object[] args);

}
