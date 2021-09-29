package org.example.beans.factory.config;

public interface BeanPostProcessor {

    /**
     * 在bean对象初始化之前，执行此方法
     */

    Object postProcessBeforeInitialization(Object bean,String beanName);


    /**
     * 在bean对象执行方法之后，执行此方法
     */
   Object postProcessAfterInitialization(Object bean,String beanName);


}
