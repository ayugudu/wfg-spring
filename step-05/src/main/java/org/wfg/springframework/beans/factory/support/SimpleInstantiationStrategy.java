package org.wfg.springframework.beans.factory.support;

import org.wfg.springframework.BeansException;
import org.wfg.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy {



    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor con, Object[] args) {
       Class clazz = beanDefinition.getBeanClass();
       try{if(con!=null){
        return    clazz.getDeclaredConstructor(con.getParameterTypes()).newInstance(args);
       }else{
          return  clazz.getDeclaredConstructor().newInstance();
       }}catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }

}
}
