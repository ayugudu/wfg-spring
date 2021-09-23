package org.example.factory.support;

import org.example.factory.BeanFactory;
import org.example.factory.config.BeanDefinition;

/**
 * 此类是对getbean 的实现具有模板方法的思想
 */
public abstract  class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    /**
     * getbean 获取单例bean的逻辑
     * 后面的方法交给 子类实现
     * @param name
     * @return
     */
    @Override
    public Object getBean(String name) {

        Object bean= getSingleBean(name);
        if(bean!=null){
           return  bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);

        return  createBean(name,beanDefinition);
    }

    protected   abstract BeanDefinition getBeanDefinition(String name);
    protected  abstract  Object createBean(String name,BeanDefinition beanDefinition);

}
