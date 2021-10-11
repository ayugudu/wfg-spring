package org.example.beans.factory.support;

import org.example.beans.factory.config.AutowireCapableBeanFactory;
import org.example.beans.factory.config.BeanDefinition;
import org.example.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory  {


    private InstantiationStrategy instantiationStrategy  =new CglibSubclassingInstantiationStrategy();



    private  final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try{
            bean = res
        }

    }

    protected  Object resolveBeforeInstantiation(String beanName,BeanDefinition beanDefinition){

        Object bean = applyBean

    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass ,String beanName){
        for(BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){



        }
    }

    public List<BeanPostProcessor> getBeanPostProcessors(){
        return  this.beanPostProcessors;
    }

}
