package org.example.context;

import org.example.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 刷新 容器
     */

    void refresh() throws BeansException;

    void registerShutdownHook();


    void close();


}
