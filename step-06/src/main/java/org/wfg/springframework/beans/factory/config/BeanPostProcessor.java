package org.wfg.springframework.beans.factory.config;

/**
 * 用于修改实力化bean
 */
public interface BeanPostProcessor {

    /**
 * 在bean对象执行初始化方法前，执行此方法
 */

Object postProcessBeforeInitialization(Object bean ,String beanName);

/**
 * 在bean 对象初始化方法后，执行此方法
 */

Object postProcessAfterInitialization(Object bean,String beanName);





}
