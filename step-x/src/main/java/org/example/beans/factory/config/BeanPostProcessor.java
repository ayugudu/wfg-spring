package org.example.beans.factory.config;

public interface BeanPostProcessor {

    /**
     * 在bean对象初始化之前开始执行
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean,String beanName) throws Exception;


    /**
     * 对象初始化之后开始执行
     * @param bean
     * @param beanName
     * @return
     */
   Object postProcessAfterInitialization(Object bean,String beanName);

}
