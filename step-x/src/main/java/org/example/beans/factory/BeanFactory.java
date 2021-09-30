package org.example.beans.factory;

import org.example.beans.BeansException;

public interface BeanFactory {
  Object getBean(String name )throws BeansException;

  Object getBean(String name,Object[] args)throws BeansException;

  <T> T getBean(String name ,Class<T> beanClass)throws BeansException;

}
