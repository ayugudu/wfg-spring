package org.example.beans.factory;

public interface BeanFactoryAware extends  Aware{
    void setBeanFactory(BeanFactory beanFactory);
}
