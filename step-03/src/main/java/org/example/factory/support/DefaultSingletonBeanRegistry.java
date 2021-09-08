package org.example.factory.support;

import org.example.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于实现beanDefinition的存储
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String,Object> singletonObjects = new HashMap<>();
    @Override
    public Object getSingleton(String beanName) {
        return  singletonObjects.get(beanName);
    }

    public void addSingleton(String beanName,Object bean){
        singletonObjects.put(beanName, bean);
    }
}
