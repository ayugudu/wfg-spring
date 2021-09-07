package org.example.factory.support;

import org.example.factory.config.BeanDefinition;

/**
 * 此类的作用就是 实现createBean
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
          try{
              bean= beanDefinition.getBeanClass().newInstance();
          } catch (InstantiationException | IllegalAccessException e) {
              e.printStackTrace();
          }
          addSingleBean(beanName,bean);
         return bean;
    }
}
