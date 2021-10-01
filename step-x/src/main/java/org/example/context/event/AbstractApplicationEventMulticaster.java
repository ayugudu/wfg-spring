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

  public final Set<ApplicationListener<ApplicationEvent>> applicationListenerSet = new LinkedHashSet<>();

  private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListenerSet.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
       applicationListenerSet.remove(listener);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {

    }



    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
     this.beanFactory=beanFactory;
    }
    /**
     * 返回给定事件类型匹配的ApplictionListerners 集合
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event){
        LinkedList<ApplicationListener> applicationListeners = new LinkedList<>();
        for(ApplicationListener<ApplicationEvent> listener :applicationListenerSet){
            if(supportsEvent(listener,event))  applicationListeners.add(listener);
        }
        return  applicationListeners;
    }
    /**
     * 监听器是否对事件感兴趣
     */

    protected  boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener,ApplicationEvent event){
        Class<? extends  ApplicationListener> listenerClass = applicationListener.getClass();
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType)genericInterface) . getActualTypeArguments()[0];

        String className = actualTypeArgument.getTypeName();

        Class<?> eventClassName ;

        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        //判定此
        return  eventClassName.isAssignableFrom(event.getClass());
    }
}
