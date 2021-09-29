package org.example.beans.factory.config;

import org.example.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行beanpostProcessors 接口实现类的 postProcessBeforeIntialization
     * @param existing
     * @param beanName
     * @return
     */

    Object applyBeanPostProcessorsBeforeInitialization(Object existing,String beanName);

    /**
     * 执行 beanPostrProcessors 接口实现了类的 postProcessorsAfterInitialization方法
     * @param existing
     * @param beanName
     * @return
     */


    Object applyBeanPostProcessorsAfterInitialization(Object existing,String beanName);


}
