package org.example.beans.factory.config;

import org.example.beans.factory.BeanFactory;

public  interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 给工厂提供扩展方法 执行接口实现类PostProcessBeforeIntialization
     */

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName);

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName);
}
