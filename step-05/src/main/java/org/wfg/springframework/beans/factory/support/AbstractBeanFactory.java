package org.wfg.springframework.beans.factory.support;

import org.wfg.springframework.beans.factory.BeanFactory;
import org.wfg.springframework.beans.factory.config.BeanDefinition;

/**
 *
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name,args);
    }


    @Override
    public <T> T getBean(String name, Class<T> requiredTYpe) {
        return (T) getBean(name);
    }

    protected  <T> T doGetBean(final String name,final Object[] args){
        Object bean = getSingleton(name);
        if (bean!=null) {
          return (T) bean;
          }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name,beanDefinition,args);
        }

        protected abstract BeanDefinition getBeanDefinition(String beanName);

       protected  abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args);



    }

