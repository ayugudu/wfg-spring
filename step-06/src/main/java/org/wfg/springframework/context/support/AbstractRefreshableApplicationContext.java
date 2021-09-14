package org.wfg.springframework.context.support;

import org.wfg.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.wfg.springframework.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() {
        DefaultListableBeanFactory beanFactory = createBeanFactory();

        loadBeanDefinitions(beanFactory);

        this.beanFactory=beanFactory;


    }

    private DefaultListableBeanFactory createBeanFactory(){
        return  new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
