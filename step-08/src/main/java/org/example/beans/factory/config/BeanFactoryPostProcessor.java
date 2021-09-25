package org.example.beans.factory.config;

import org.example.beans.BeansException;
import org.example.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
    /**
     * 在所有 beanDefinition 加载完成后，提供修改beanDefinition属性的机制
     */
    void  postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
