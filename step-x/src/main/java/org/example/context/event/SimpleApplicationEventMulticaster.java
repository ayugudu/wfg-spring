package org.example.context.event;

import org.example.beans.factory.BeanFactory;
import org.example.context.ApplicationEvent;
import org.example.context.ApplicationListener;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory factory){
        setBeanFactory(factory);
    }

    public void multicastEvent(final ApplicationEvent event){
        for(final ApplicationListener listener : getApplicationListeners(event)){
            listener.onApplicationEvent(event);
        }
    }

}
