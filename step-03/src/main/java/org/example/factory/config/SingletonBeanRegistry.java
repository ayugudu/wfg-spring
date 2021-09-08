package org.example.factory.config;

/**
 * 用于定义获取单例对象
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
