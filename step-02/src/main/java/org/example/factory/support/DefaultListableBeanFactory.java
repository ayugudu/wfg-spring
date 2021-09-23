package org.example.factory.support;

import org.example.factory.BeansException;
import org.example.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 此类是最终的实现类
 *
 */
public class DefaultListableBeanFactory extends  AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

    private Map<String,BeanDefinition>  beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String name) {

        BeanDefinition   beanDefinition= beanDefinitionMap.get(name);
        if(beanDefinition==null)throw  new BeansException("No bean named '" + name + "' is defined");
        return  beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
          beanDefinitionMap.put(name,beanDefinition);
    }
}
