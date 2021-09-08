package org.example.factory.support;

import org.example.factory.BaseException;
import org.example.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instant(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object... args) {
       Class clazz= beanDefinition.getBeanClass();
     try{
       if(args!=null){

           return  clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
       }else{
           return  clazz.getDeclaredConstructor().newInstance();
       }
     }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BaseException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
