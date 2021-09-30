package org.example.beans.factory;

import org.example.beans.BeansException;

public interface BeanFactoryAware extends Aware{
    void setBeanFactory(BeanFactory beanFactory)throws BeansException;

}
