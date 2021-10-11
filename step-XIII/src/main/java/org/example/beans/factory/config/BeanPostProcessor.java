package org.example.beans.factory.config;

public interface BeanPostProcessor {

    /**
     *  在对象执行初始化 方法之前
     */
    Object postProcessBeforeInitialization(Object bean,String beanName );


    /**
     * 在对象执行初始化 方法之后执行 此方法
     */

    Object postProcessAfterInitialization(Object bean,String beanName);


}
