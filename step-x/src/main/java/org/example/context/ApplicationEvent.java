package org.example.context;

import java.util.EventObject;

public abstract class ApplicationEvent extends EventObject {

    //构建一个原型事件

    public ApplicationEvent(Object o) {
        super(o);
    }
}
