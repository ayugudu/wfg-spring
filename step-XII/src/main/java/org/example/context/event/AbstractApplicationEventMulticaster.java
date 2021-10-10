package org.example.context.event;

import org.example.beans.BeansException;
import org.example.beans.factory.BeanFactory;
import org.example.beans.factory.BeanFactoryAware;
import org.example.context.ApplicationEvent;
import org.example.context.ApplicationListener;
import org.example.util.ClassUtils;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public abstract class AbstractApplicationEventMulticaster implements  ApplicationEventMulticaster, BeanFactoryAware {


    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 返回监听器的集合
     */

    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event){
        LinkedList<ApplicationListener> applicationListeners = new LinkedList<>();

        for(ApplicationListener<ApplicationEvent> listener :applicationListeners){
            if(supportsEvent(listener,event))
                applicationListeners.add(listener);
        }

        return applicationListeners;
    }

    protected  boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener,ApplicationEvent event){
        Class<? extends  ApplicationListener> listenerClass = applicationListener.getClass();

        //        获取此类的第一个实现接口的第一个泛型参数类型Type

        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass():listenerClass;

        Type  genericInterface  = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument =  ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();

       Class<?> eventClassName;

       try{
           eventClassName =Class.forName(className);
       } catch (ClassNotFoundException e) {
           throw new BeansException("wrong event class name: " + className);
       }

       return  eventClassName.isAssignableFrom(event.getClass());
    }
}
