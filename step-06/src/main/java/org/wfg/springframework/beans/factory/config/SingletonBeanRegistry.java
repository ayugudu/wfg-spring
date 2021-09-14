package org.wfg.springframework.beans.factory.config;

/**
 * 单例缓存
 */
public interface SingletonBeanRegistry {

Object getSingleton(String beanName);


}
