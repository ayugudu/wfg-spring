package org.example.beans_x;

import org.example.context.ApplicationListener;
import org.example.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件："+this.getClass().getName());
    }
}
