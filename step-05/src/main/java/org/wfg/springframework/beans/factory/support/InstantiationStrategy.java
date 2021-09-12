package org.wfg.springframework.beans.factory.support;

import org.wfg.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 定义实力化策略
 */
public interface InstantiationStrategy {
 Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor con, Object[] args);

}
