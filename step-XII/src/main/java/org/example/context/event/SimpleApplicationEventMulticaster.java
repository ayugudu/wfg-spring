package org.example.context.event;

import org.example.beans.factory.BeanFactory;
import org.example.context.ApplicationEvent;
import org.example.context.ApplicationListener;

public class SimpleApplicationEventMulticaster  extends  AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory){
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for(final ApplicationListener listener : getApplicationListeners(event)){
            listener.onApplicationEvent(event);
        }
    }
}
