package org.example.factory.support;

import org.example.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 使用策略模式，解决创建对象的不同方式
 */
public interface InstantiationStrategy {

     Object instant( BeanDefinition beanDefinition,String beanName, Constructor constructor,Object... args);

}
