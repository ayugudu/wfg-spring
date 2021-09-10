package org.example.factory.support;

import org.example.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

      private Map<String,Object> singleObjects = new HashMap<>();

    @Override
    public Object getSingle(String name) {
        return singleObjects.get(name);
    }

    public void addSingle(String name,Object bean){
        singleObjects.put(name,bean);
    }
}
