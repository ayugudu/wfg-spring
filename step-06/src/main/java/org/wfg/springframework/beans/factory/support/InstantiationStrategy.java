package org.wfg.springframework.beans.factory.support;


import org.wfg.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 策略模式，实例化bean
 */
public interface InstantiationStrategy {

Object instantiate(BeanDefinition beanDefinition, Constructor ctor, Object[] args);

}
