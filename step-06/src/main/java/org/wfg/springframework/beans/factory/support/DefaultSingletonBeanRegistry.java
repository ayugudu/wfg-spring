package org.wfg.springframework.beans.factory.support;

import org.wfg.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例缓存
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String,Object> singleObjects = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return singleObjects.get(beanName);
    }

    protected void addSingleton(String beanName,Object singletonObject){
        singleObjects.put(beanName, singletonObject);
    }
}
