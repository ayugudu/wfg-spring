package org.example.beans.factory.config;

import org.example.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {


    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName);


    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName);


}
