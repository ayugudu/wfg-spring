package org.example.beans_x;

import org.example.context.ApplicationListener;
import org.example.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件："+this.getClass().getName());
    }
}
