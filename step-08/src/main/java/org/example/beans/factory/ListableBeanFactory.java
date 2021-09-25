package org.example.beans.factory;

import org.example.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{

    /**
     *
     */
    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException;




    String[] getBeanDefinitionNames();

}
