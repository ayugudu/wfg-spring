package spring.beans.factory.support;

import spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 *实例化操作
 */
public interface InstantiationStrategy {

  Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor,Object[] args);

}
