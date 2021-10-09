package org.example.beans.factory.config;

public interface BeanPostProcessor {

    Object PostProcessBeforeInitialization(Object bean,String beanName);




    Object postProcessAfterInitialization(Object bean,String beanName);
}
