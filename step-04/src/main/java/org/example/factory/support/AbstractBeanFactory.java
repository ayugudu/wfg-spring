package org.example.factory.support;

import org.example.factory.config.BeanDefinition;

/**
 *  使用模板方法，定义了获取getBean的方法
 */
public abstract class AbstractBeanFactory  extends DefaultSingletonBeanRegistry implements BeanFactory{

    @Override
    public Object getBean(String beanName) {
      return doGetBean(beanName,null);
    }

    public Object getBean(String beanName,Object... args){
        return  doGetBean(beanName,args);
    }

    protected <T> T doGetBean(final String beanName ,final Object [] args){
        Object bean = getSingle(beanName);
        if(bean!=null){
            return (T)bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanDefinition,beanName, args);

    }





    public abstract BeanDefinition getBeanDefinition(String name);

    public abstract  Object createBean(BeanDefinition beanDefinition, String name,Object [] args);
}
