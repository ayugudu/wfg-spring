package org.wfg.springframework.beans.factory.support;

import org.wfg.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单列缓存
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

   private Map<String,Object> singletonObject = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return singletonObject.get(beanName);
    }

    protected  void addSingleton(String beanName,Object bean){
        singletonObject.put(beanName, bean);
    }
}
