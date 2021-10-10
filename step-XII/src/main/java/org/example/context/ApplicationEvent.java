package org.example.context;

import java.util.EventObject;

/**
 *
 * 观察者模式
 * 事件的抽象
 * 将方法逻辑抽象成事件与 监听器
 *
 */
public  abstract class ApplicationEvent extends EventObject {



    public ApplicationEvent(Object o) {
        super(o);
    }
}
