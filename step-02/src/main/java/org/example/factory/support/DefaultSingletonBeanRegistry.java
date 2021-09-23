package org.example.factory.support;

import org.example.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String ,Object> singletonObjects= new HashMap<>();

    @Override
    public Object getSingleBean(String name) {
        return singletonObjects.get(name);
    }

    public void addSingleBean(String name,Object bean){
        singletonObjects.put(name,bean);
    }
}
