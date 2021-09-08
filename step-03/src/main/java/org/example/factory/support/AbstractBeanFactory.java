package org.example.factory.support;

import org.example.factory.BeanFactory;
import org.example.factory.config.BeanDefinition;

/**
 * 模板方法模式，定义获取bean的 主要流程
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

    public <T> T doGetBean(final String beanName,final Object[] args){
        Object bean = getSingleton(beanName);
        if(bean!=null){
            return  (T)bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
         return (T) createBean(beanName,beanDefinition,args);
    }

    protected  abstract  BeanDefinition getBeanDefinition(String name);
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args);
}
