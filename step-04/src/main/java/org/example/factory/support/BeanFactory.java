package org.example.factory.support;

/**
 * 定义 容器
 */
public interface BeanFactory {
    public Object getBean(String beanName);
    public Object getBean(String beanName,Object... args);
}
