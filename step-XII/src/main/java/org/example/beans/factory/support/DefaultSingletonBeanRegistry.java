package org.example.beans.factory.support;

import org.example.beans.factory.DisposableBean;
import org.example.beans.factory.config.SingletonBeanRegistry;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected  static final Object NULL_OBJECT = new Object();

    private Map<String,Object> singletonObjects =new ConcurrentHashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
       singletonObjects.put(beanName, singletonObject);
    }


    public void registerDisposableBean(String beanName,DisposableBean bean){
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons()throws Exception{
        Set<String> keySet =this.disposableBeans.keySet();

        Object[] disposableBeanNames= keySet.toArray(new String[0]);


        for(int i = disposableBeanNames.length-1;i>=0;i--){
            Object beanName = disposableBeanNames[i];

            DisposableBean disposableBean = disposableBeans.remove(beanName);

            disposableBean.destroy();

        }






    }




}