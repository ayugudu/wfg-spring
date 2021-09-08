package org.example.factory.support;

import org.example.factory.BaseException;
import org.example.factory.BeanFactory;
import org.example.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户端操作类
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
   private Map<String,BeanDefinition> beanDefinitionMap =   new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String name) {
      BeanDefinition beanDefinition= beanDefinitionMap.get(name);
        if (beanDefinition ==null) {
          throw new BaseException("No bean named '" + name + "' is defined");
        }

       return  beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
          beanDefinitionMap.put(beanName,beanDefinition);
    }
}
