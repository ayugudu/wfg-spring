package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor,Object[] args) throws BeansException;
}
