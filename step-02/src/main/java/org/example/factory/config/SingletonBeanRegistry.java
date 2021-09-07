package org.example.factory.config;

/**
 * 提供 获取单列bean的流程
 */
public interface SingletonBeanRegistry {

    Object getSingleBean(String name);

}
