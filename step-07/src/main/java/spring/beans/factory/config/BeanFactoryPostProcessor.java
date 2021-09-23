package spring.beans.factory.config;

import spring.beans.factory.ConfigurableListableBeanFactory;

/**
 * 扩展beanDefinition
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在beanDefinition 加载完成后，实例化bean
     */
  void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);

}
