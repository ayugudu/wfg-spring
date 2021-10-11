package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class FactoryBeanRegistrySupport extends  DefaultSingletonBeanRegistry{

    private final Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected  Object getCacheObjectForFactoryBean(String beanName){
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    protected  Object getObjectFromFactoryBean(FactoryBean factoryBean,String beanName){
        if(factoryBean.isSingleton()){
            Object object = this.factoryBeanObjectCache.get(beanName);
            if(object == null){
                object =doGetObjectFromFactoryBean(factoryBean, beanName);
                this.factoryBeanObjectCache.put(beanName,(object != null ? object : NULL_OBJECT));
            }
            return (object != NULL_OBJECT ? object : null);

        }else{
           return doGetObjectFromFactoryBean(factoryBean, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factoryBean,final String beanName){
        try{
            return  factoryBean.getObject();
        }catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }



    }




}
