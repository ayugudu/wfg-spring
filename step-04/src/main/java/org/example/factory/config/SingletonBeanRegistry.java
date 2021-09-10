package org.example.factory.config;

/**
 * 定义注册表
 */
public interface SingletonBeanRegistry {

 Object getSingle(String name);

}
