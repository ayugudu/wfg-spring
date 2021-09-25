package org.example.beans.factory.config;

/**
 * 提供 扩展接口
 */
public interface BeanPostProcessor {

    /**
     * 在bean 对象执行初始化方法之前，执行此方法
     * @param bean
     * @param beanName
     * @return
     */
   Object postProcessBeforeInitialization(Object bean,String beanName);

    /**
     * 在bean对象执行初始化方法后 执行此方法
     * @param bean
     * @param beanName
     * @return
     */
   Object postProcessAfterInitialization(Object bean,String beanName);



}
