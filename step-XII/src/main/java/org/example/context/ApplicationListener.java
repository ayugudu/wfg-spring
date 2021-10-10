package org.example.context;

import java.util.EventListener;

/**
 * 观察者对象
 * @param <E>
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    /**
     *  方法逻辑抽象
     * @param e
     */
   void onApplicationEvent(E e);
}
