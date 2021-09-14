package org.wfg.springframework.context.support;

import org.wfg.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.wfg.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.wfg.springframework.beans.factory.config.BeanPostProcessor;
import org.wfg.springframework.context.ConfigurableApplicationContext;
import org.wfg.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 上下文 把相关的东西放到此类
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


    @Override
    public void refresh() throws Exception {
        //1 创建BeanFactory ，并加载BeanDefinition
        refreshBeanFactory();

        // 2 获取 beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3在 bean 实力化之前 ，执行 BeanFactoryPostProcessor

        invokeBeanFactoryPostProcessors(beanFactory);
        //4 将 beanPostProcessor 在对象实力化之前进行注册操作
         registerBeanPostProcessors(beanFactory);

        // 5 预 实例化
        beanFactory.preInstantiateSingletons();
    }

    protected  abstract void refreshBeanFactory();

    protected  abstract ConfigurableListableBeanFactory getBeanFactory();


    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) throws Exception {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor: beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        }
    }

     private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor:beanPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
     }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    public String[] getBeanDefinitionNames(){
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return  getBeanFactory().getBean(beanName,requiredType);
    }

    @Override
    public Object getBean(String beanName) {
        return  getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return getBeanFactory().getBean(beanName, args);
    }
}
