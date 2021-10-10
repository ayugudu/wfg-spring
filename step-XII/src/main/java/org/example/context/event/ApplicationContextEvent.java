package org.example.context.event;

import org.example.context.ApplicationContext;
import org.example.context.ApplicationEvent;

public class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(Object o) {
        super(o);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();

    }



}
