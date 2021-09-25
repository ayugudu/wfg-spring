package org.example.beans.factory.support;

import org.example.beans.factory.config.BeanDefinition;
import org.example.beans.factory.config.BeanPostProcessor;
import org.example.beans.factory.config.ConfigurableBeanFactory;
import org.example.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public  abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();


    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();


    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName,null);
    }

    @Override
    public Object getBean(String beanName, Object[] args) {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> clazz) {
        return (T) getBean(beanName);
    }

    protected <T> T doGetBean(final String name, final Object[] args){
        Object bean = getSingleton(name);
        if(bean != null){
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);

        return (T) createBean(name,beanDefinition,args);


    }
    protected  abstract BeanDefinition getBeanDefinition(String beanName);

    protected  abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object [] args);




    /**
     * 刷新机制
     * @param beanPostProcessor
     */
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
}
