package org.example.beans.factory.support;

import org.example.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 定义实力化
 */
public interface InstantiationStrategy {


    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,Object[] args);

}
