package org.example.factory;

/**
 * 获取对象
 */
public interface BeanFactory {
    Object getBean(String name);
    Object getBean(String name,Object... args);
}
