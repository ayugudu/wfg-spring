package org.example.context.support;

import org.example.beans.BeansException;
import org.example.beans.factory.ConfigurableListableBeanFactory;
import org.example.beans.factory.config.BeanFactoryPostProcessor;
import org.example.beans.factory.config.BeanPostProcessor;
import org.example.context.ConfigurableApplicationContext;
import org.example.core.io.DefaultResourceLoader;

import java.util.Map;

public  abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

   public void refresh(){
       //创建BeanFactory,加载beanDefinition

       refreshBeanFactory();

       ConfigurableListableBeanFactory beanFactory = getBeanFactory();

       //扩展
       beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
       //  在 Bean 实例化之前，执行 BeanFactoryPostProcessor
       invokeBeanFactoryPostProcessors(beanFactory);
       // 5. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
       registerBeanPostProcessors(beanFactory);

       // 提前实例化单例bean
       beanFactory.preInstantiateSingletons();
   }

   protected  abstract void refreshBeanFactory();

   protected  abstract  ConfigurableListableBeanFactory getBeanFactory();

   private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
       Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
       for(BeanFactoryPostProcessor beanFactoryPostProcessor:beanFactoryPostProcessorMap.values()){
           beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
       }
   }
  private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
       Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
       for(BeanPostProcessor beanPostProcessor:beanPostProcessorMap.values()){
           beanFactory.addBeanPostProcessor(beanPostProcessor);
       }
  }



  public String[] getBeanDefinitionNames(){
       return  getBeanFactory().getBeanDefinitionNames();
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

    public <T> Map<String,T> getBeansOfType(Class<T> type){
       return  getBeanFactory().getBeansOfType(type);
    }

    public void registerShutdownHook(){
       Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    public void close(){
       getBeanFactory().destroySingletons();
    }

}
