package org.wfg.springframework.beans.factory.config;


import org.wfg.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * 定义工厂要实现的配置接口
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory ,SingletonBeanRegistry  {

     String SCOPE_SINGLETON = "singleton";

     String SCOPE_PROTOTYPE = "prototype";

     void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
