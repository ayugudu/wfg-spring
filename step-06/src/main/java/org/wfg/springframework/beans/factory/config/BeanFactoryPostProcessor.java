package org.wfg.springframework.beans.factory.config;

import org.wfg.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * beanDefinition 扩展机制
 */
public interface BeanFactoryPostProcessor {
    /**
     *在所有beanDefinition加载完成后，实例化对象前，提供修改BeanDefinition 属性机制
     * @param beanFactory
     */
  void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws Exception;

}
