package org.example.beans.factory.config;

import org.example.beans.factory.BeanFactory;

/**
 * bean factory的扩展接口
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行初始化方法之前
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName );


    /**
     * 执行初始化 方法后的扩展
     */

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName);















}
