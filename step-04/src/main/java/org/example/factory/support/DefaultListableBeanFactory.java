package org.example.factory.support;

import org.example.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {
   private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();


    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }

    @Override
    public void registerBeanDefinition(String beanName,BeanDefinition beanDefinition) {
         beanDefinitionMap.put(beanName, beanDefinition);
    }
}
