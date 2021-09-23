package spring.context.support;

import spring.beans.BeansException;
import spring.beans.factory.ConfigurableListableBeanFactory;
import spring.beans.factory.config.BeanFactoryPostProcessor;
import spring.beans.factory.config.BeanPostProcessor;
import spring.context.ConfigurableApplicationContext;
import spring.core.io.DefaultResourceLoader;

import java.util.Map;

public abstract  class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


    @Override
    public void refresh() throws BeansException {
        // 创建 beanFactory,并加载beanDefinition
        refreshBeanFactory();
        //获取 beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //在bean 是实例化之前，执行 beanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 执行注册操作
        registerBeanPostProcessors(beanFactory);

        //提前实例化
        beanFactory.preInstantiateSingletons();

    }
    protected abstract  void refreshBeanFactory();

    protected  abstract  ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap= beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String , BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
    for(BeanPostProcessor beanPostProcessor:beanPostProcessorMap.values()){
        beanFactory.addBeanPostProcessor(beanPostProcessor);
    }


    }
    public <T> Map<String,T> getBeansOfType(Class<T> type){
        return getBeanFactory().getBeansOfType(type);
    }

    public String [] getBeanDefinitionNames(){
        return  getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }


    public Object getBean(String name,Object...args){
        return  getBeanFactory().getBean(name,args);
    }

    public <T> T getBean(String name ,Class<T> requiredType){
        return  getBeanFactory().getBean(name,requiredType);
    }

    public void registerShutdownHook(){
        //钩子函数，用于对象销毁和内存处理
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }
    public void close(){

        getBeanFactory().destroySingletons();
    }

}


