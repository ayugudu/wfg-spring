package org.wfg.springframework.beans.factory;

import java.util.Map;

/**
 * 预加载bean定义的beanFactory 的实现
 */
public interface ListableBeanFactory  extends BeanFactory{
/**
 *  按照类型返回bean实例
 */

<T> Map<String,T> getBeansOfType(Class<T> type);

/**
 * 返回注册表中的所有bean名称
 */

String [] getBeanDefinitionNames();
}
