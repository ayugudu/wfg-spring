package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements  InstantiationStrategy{


    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        Class clazz = beanDefinition.getBeanClass();
        try{
            if(null!=ctor){
                return  clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);

            }else{
                return  clazz.getDeclaredConstructor().newInstance();
            }
        }  catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
