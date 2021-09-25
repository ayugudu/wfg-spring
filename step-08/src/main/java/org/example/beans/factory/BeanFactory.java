package org.example.beans.factory;

/**
 * bean 工厂 用于获取 bean
 */
public interface BeanFactory {
   Object getBean(String beanName);

   Object  getBean(String beanName ,Object[] args);

   <T> T getBean (String beanName ,Class<T> clazz);

}
