package org.example.beans.factory;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{

    String [] getBeanDefinitionNames();

    <T> Map<String,T> getBeansOfType(Class<T> tyoe);
}
