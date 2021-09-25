package org.example.beans.factory.config;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
