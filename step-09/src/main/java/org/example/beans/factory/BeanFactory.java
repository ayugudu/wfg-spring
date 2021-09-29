package org.example.beans.factory;

public interface BeanFactory {

    Object getBean(String name);

    Object getBean(String name ,Object... args);


    <T> T getBean(String beanName,Class<T> beanClass);



}
