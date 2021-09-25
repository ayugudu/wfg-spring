package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.DisposableBean;
import org.example.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private Map<String ,Object> objectMap = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    public void addSingleton(String name,Object bean){
         objectMap.put(name, bean);
    }


    @Override
    public Object getSingleton(String beanName) {
        return null;
    }

    /**
     *
     */
    public void registerDisposableBean(String beanName,DisposableBean disposableBean){
        disposableBeanMap.put(beanName, disposableBean);
    }

    /**
     * 执行销毁方法，用于销毁容器中的数据
     */
    public void destroySingletons(){

        Set<String> keySet =this.disposableBeanMap.keySet();

        Object[] disposableBeanName = keySet.toArray();

        for(int i =disposableBeanName.length-1;i>=0;i--){
            Object beanName =disposableBeanName[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
          try{
              disposableBean.destroy();
          }catch (Exception e){
              throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
          }

        }


    }


}
