package org.example.context.support;

import org.example.beans.factory.config.BeanPostProcessor;
import org.example.context.ApplicationContext;
import org.example.context.ApplicationContextAware;

public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
       if(bean instanceof ApplicationContextAware){
           ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
       }
       return  bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
