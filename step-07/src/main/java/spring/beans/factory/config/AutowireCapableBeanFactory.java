package spring.beans.factory.config;

import spring.beans.factory.BeanFactory;

/**
 * 定义可以扩展的工厂
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行BeanPostProcessors 的postProcessBeforeInitialization
     * @param existingBean
     * @param beanName
     * @return
     */
 Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName);

    /**
     * 执行 BeanPostProcessors的 postprocessAfterInitialization
     * @return
     */
 Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName);



}
