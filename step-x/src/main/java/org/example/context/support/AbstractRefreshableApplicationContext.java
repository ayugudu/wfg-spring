package org.example.context.support;

import org.example.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends  AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    protected DefaultListableBeanFactory createBeanFactory(){
        return  new DefaultListableBeanFactory();
    }

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
