package org.example.factory;

/**
 * 工厂，只提供获取bean的信息
 */
public interface BeanFactory {
    Object getBean(String name);
}
