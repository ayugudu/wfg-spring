package org.example.context.event;

import org.example.context.ApplicationEvent;
import org.example.context.ApplicationListener;

/**
 * 事件广播器
 * 被观察者对象的抽象
 *
 */
public interface ApplicationEventMulticaster {

     void addApplicationListener(ApplicationListener<?> listener);


     void removeApplicationListener(ApplicationListener<?> listener);


     void multicastEvent(ApplicationEvent event);


}
