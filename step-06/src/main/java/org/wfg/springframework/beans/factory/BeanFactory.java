package org.wfg.springframework.beans.factory;

/**
 * 定义工厂获取bean
 */
public interface BeanFactory {

  Object getBean(String beanName);

  Object getBean(String beanName,Object... args);

  <T> T getBean(String beanName,Class<T> requiredType);






}
