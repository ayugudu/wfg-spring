package org.wfg.springframework.beans.factory;

import org.wfg.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.wfg.springframework.beans.factory.config.BeanDefinition;
import org.wfg.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends  ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
 BeanDefinition getBeanDefinition(String beanName);

}
