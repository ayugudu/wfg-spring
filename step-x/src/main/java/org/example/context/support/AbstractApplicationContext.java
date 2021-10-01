package org.example.context.support;

import org.example.beans.BeansException;
import org.example.beans.factory.ConfigurableListableBeanFactory;
import org.example.beans.factory.config.BeanFactoryPostProcessor;
import org.example.beans.factory.config.BeanPostProcessor;
import org.example.beans.factory.support.DefaultSingletonBeanRegistry;
import org.example.context.ApplicationEvent;
import org.example.context.ApplicationListener;
import org.example.context.ConfigurableApplicationContext;
import org.example.context.event.ApplicationEventMulticaster;
import org.example.context.event.ContextClosedEvent;
import org.example.context.event.ContextRefreshedEvent;
import org.example.context.event.SimpleApplicationEventMulticaster;
import org.example.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME="applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    public void refresh(){
        // 1 创建beanFactory
        refreshBeanFactory();
        // 获取bean Factory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        invokeBeanFactoryPostProcessor(beanFactory);


        registerBeanPostProcessors(beanFactory);

        // 初始化时间发布者
        initApplicationEventMulticaster();

        //  注册事件监听器
        registerListeners();

        // 提前实例化单列bean对象
        beanFactory.preInstantiateSingletons();

        //容器刷新完成事件
        finishRefresh();

    }

    protected  void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,applicationEventMulticaster);
    }

    protected  void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = getBeanFactory().getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }


    }

    private void registerListeners(){
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for(ApplicationListener listener:applicationListeners){
            applicationEventMulticaster.addApplicationListener(listener);
        }

    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);

        for(BeanPostProcessor beanPostProcessor:beanPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

     private void finishRefresh(){
        publishEvent(new ContextRefreshedEvent(this));
     }
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory();

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    public void registerShutdownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }
    public void close(){
        publishEvent(new ContextClosedEvent(this));
        getBeanFactory().destroySingletons();
    }
}
