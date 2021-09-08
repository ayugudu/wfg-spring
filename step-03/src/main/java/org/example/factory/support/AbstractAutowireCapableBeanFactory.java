package org.example.factory.support;


import org.example.factory.BaseException;
import org.example.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 用于实现 createBean 方法，而createBean 需要实例化，实例化的策略
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
  private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean =null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        }catch (Exception e){
            throw new BaseException("Instantiation of bean failed", e);
        }
        addSingleton(beanName,bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName ,Object [] args){
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?> []  declaredConstructors= beanClass.getDeclaredConstructors();
        //获取对应的构造器
        for (Constructor  ctor: declaredConstructors){
            if(null!=args && ctor.getParameterTypes().length==args.length){
                constructorToUse=ctor;
                break;
            }
        }
        return  getInstantiationStrategy().instant(beanDefinition,beanName,constructorToUse,args);

    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
