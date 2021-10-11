package org.example.beans.factory.config;

public interface SingleBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName,Object singletonObject);

}
