package org.example.context.event;

import org.example.context.ApplicationEvent;
import org.example.context.ApplicationListener;

/**
 * 广播器
 */
public interface ApplicationEventMulticaster {

    /**
     * 添加一个监听器以接受所有时间的通知
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 从通知列表中删除侦听器
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 将给定的应用程序事件多播到适当的侦听器
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
