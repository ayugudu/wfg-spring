package org.wfg.springframework.beans.factory.support;

import org.wfg.springframework.beans.factory.BeanFactory;
import org.wfg.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.wfg.springframework.beans.factory.config.BeanDefinition;
import org.wfg.springframework.beans.factory.config.BeanPostProcessor;
import org.wfg.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 经典 模板
 */
public abstract class AbstractBeanFactory  extends  DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /**
     * 在创建createBean时使用
     */
    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName,args);
    }


    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName,null);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return (T)getBean(beanName);
    }

    protected <T> T doGetBean(final String name,final Object[] args){
        Object bean = getSingleton(name);
        if(null!=bean){
            return  (T)bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
       return (T) createBean(name,beanDefinition,args);

    }

    protected abstract BeanDefinition getBeanDefinition (String beanName);

    protected  abstract Object createBean(String beanName ,BeanDefinition beanDefinition,Object[] args);

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessorList.remove(beanPostProcessor);
        this.beanPostProcessorList.add(beanPostProcessor);
    }


    public List<BeanPostProcessor> getBeanPostProcessorList(){
        return  this.beanPostProcessorList;
    }

}
