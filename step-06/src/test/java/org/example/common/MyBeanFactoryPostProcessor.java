package org.example.common;

import org.wfg.springframework.beans.PropertyValue;
import org.wfg.springframework.beans.PropertyValues;
import org.wfg.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.wfg.springframework.beans.factory.config.BeanDefinition;
import org.wfg.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws Exception {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues  = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company","san he"));
    }
}
