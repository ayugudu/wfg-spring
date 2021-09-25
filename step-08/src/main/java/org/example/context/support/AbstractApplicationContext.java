package org.example.context.support;

import org.example.beans.BeansException;
import org.example.beans.factory.ConfigurableListableBeanFactory;
import org.example.beans.factory.config.BeanFactoryPostProcessor;
import org.example.beans.factory.config.BeanPostProcessor;
import org.example.context.ConfigurableApplicationContext;
import org.example.core.io.DefaultResourceLoader;

import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() throws BeansException {

        //1 创键beanFactory，并加载 BeanDefinition
        refreshBeanFactory();
        //2 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //3 添加ApplicationContextAwareProcessor ，让继承自 ApplicationContextAware 的Bean 对象感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
       // 4 在bean实例化之前，执行BeanFactoryPostProcessor,提供了修改beanDefinition的机会
       invokeBeanFactoryPostProcessors(beanFactory);
       //5 bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);
        //6 提前实力化单列保额按对象
        beanFactory.preInstantiateSingletons();

    }


    protected abstract void refreshBeanFactory() throws  BeansException;

    protected  abstract ConfigurableListableBeanFactory getBeanFactory();

    protected  void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor:beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor: beanPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }

    }
    @Override
    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object[] args) {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> clazz) {
      return  getBeanFactory().getBean(beanName, clazz);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }



    @Override
    public void registerShutdownHook() {
     Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
       getBeanFactory().destroySingletons();
    }
}
