package spring.context.support;

import spring.beans.factory.ConfigurableListableBeanFactory;
import spring.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends  AbstractApplicationContext{
    private DefaultListableBeanFactory beanFactory;
    protected  void refreshBeanFactory(){
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory=beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory(){
        return  new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    protected ConfigurableListableBeanFactory getBeanFactory(){return  beanFactory;}
}
