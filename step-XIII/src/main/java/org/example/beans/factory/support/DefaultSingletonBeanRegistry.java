package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.DisposableBean;
import org.example.beans.factory.config.SingletonBeanRegistry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

   protected  static final Object NULL_OBJECT = new Object();

   private Map<String,Object> singletonObjects = new ConcurrentHashMap<>();

   private final Map<String, DisposableBean> disposableBeanMap = new LinkedHashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
         singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName,DisposableBean bean){
        disposableBeanMap.put(beanName, bean);
    }

    public void destroySingletons(){
        Set<String> keySet = this.disposableBeanMap.keySet();
        Object[] disposableBeanNames = keySet.toArray();
        for(int i = disposableBeanNames.length-1;i>=0;i--){
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
