package spring.context;

import spring.beans.BeansException;

public interface ConfigurableApplicationContext  extends  ApplicationContext{

     /**
      * 刷新容器
      */
      void refresh() throws BeansException;

      void registerShutdownHook();

      void close();



 }
