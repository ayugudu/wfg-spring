package org.example.beans.factory.config;

import org.example.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {


     void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);

}
