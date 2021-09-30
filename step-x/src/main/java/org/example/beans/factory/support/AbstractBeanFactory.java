package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.FactoryBean;
import org.example.beans.factory.config.BeanDefinition;
import org.example.beans.factory.config.BeanPostProcessor;
import org.example.beans.factory.config.ConfigurableBeanFactory;
import org.example.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public  abstract  class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }


    protected  <T> T doGetBean(final String beanName,final Object [] args){
        Object sharedInstance = getSingleton(beanName);
        if(sharedInstance!=null){

            return (T) getObjectForBeanInstance(sharedInstance,beanName);
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Object bean = createBean(beanName,beanDefinition,args);
        return (T) getObjectForBeanInstance(bean,beanName);
    }
    private Object getObjectForBeanInstance(Object beanInstance,String beanName){
        if(!(beanInstance instanceof FactoryBean)){
            return  beanInstance;
        }

        Object object = getCacheObjectForFactoryBean(beanName);

       if(object == null){
           FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
           object = getObjectFromFactoryBean(factoryBean,beanName);
       }
       return  object;



    }
    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract  Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args);

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }

    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return beanPostProcessorList;
    }
}
