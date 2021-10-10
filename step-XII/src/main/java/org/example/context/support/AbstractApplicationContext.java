package org.example.context.support;

import org.example.beans.BeansException;
import org.example.beans.factory.ConfigurableListableBeanFactory;
import org.example.beans.factory.config.BeanFactoryPostProcessor;
import org.example.beans.factory.config.BeanPostProcessor;
import org.example.context.ApplicationEvent;
import org.example.context.ApplicationListener;
import org.example.context.ConfigurableApplicationContext;
import org.example.context.event.ApplicationEventMulticaster;
import org.example.context.event.ContextClosedEvent;
import org.example.context.event.ContextRefreshedEvent;
import org.example.context.event.SimpleApplicationEventMulticaster;
import org.example.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME="applicationEventMulticaster";


    private ApplicationEventMulticaster applicationEventMulticaster;


    @Override
    public void refresh() {
    // 创键 beanFactory ，加载beanDefinition
        refreshBeanFactory();
    // 获取 beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
       //在bean实例化之前，执行 beanFactoryPostprocessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 注册beanPostProcessor
        registerBeanPostProcessors(beanFactory);

        // 初始化
        initApplicationEventMulticaster();

        //注册事件监听器
        registerListeners();
          // 提前实例化单列对象
        beanFactory.preInstantiateSingletons();
        // 发布容器刷新完成
        finishRefresh();


    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);

        for(BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }

    }
    private void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);

        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,applicationEventMulticaster);


    }

    private void finishRefresh(){
        publishEvent(new ContextRefreshedEvent(this));
    }

    private void registerListeners(){

        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for(ApplicationListener listener : applicationListeners){
            applicationEventMulticaster.addApplicationListener(listener);
        }
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

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
           applicationEventMulticaster.multicastEvent(event);
    }


    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
    // 发布事件
        publishEvent(new ContextClosedEvent(this));

        getBeanFactory().destroySingletons();
    }
}
