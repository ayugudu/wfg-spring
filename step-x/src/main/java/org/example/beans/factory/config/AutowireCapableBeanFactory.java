package org.example.beans.factory.config;

import org.example.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 增加扩展方法
     */

    Object applyBeanPostProcessorsBeforeInitialization(Object existing,String beanName);


    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName);


}
