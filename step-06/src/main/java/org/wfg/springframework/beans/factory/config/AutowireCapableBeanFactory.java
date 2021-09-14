package org.wfg.springframework.beans.factory.config;

import org.wfg.springframework.beans.factory.BeanFactory;

/**
 * 扩展工厂，实现能够自动装配bean
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

/**
 * 执行 beanPostProcessors 接口实现类的postProcessBeforeInitialization
 *
 */
 Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName);

    /**
     * 执行 BeanPostProcessors 接口实现的 postProcessorsAfterInitialization
     */

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName);

}
