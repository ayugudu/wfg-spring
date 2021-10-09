package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.ConfigurableListableBeanFactory;
import org.example.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements  BeanDefinitionRegistry, ConfigurableListableBeanFactory {

   private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();




    @Override
    public void preInstantiateSingletons() {
     beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
       Map<String,T> result = new HashMap<>();
       beanDefinitionMap.forEach((beanName,beanDefinition)->{
           Class beanClass= beanDefinition.getBeanClass();
           if(type.isAssignableFrom(beanClass)){
               result.put(beanName, (T) getBean(beanName));
           }
       });
       return  result;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName,beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
       BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
       if(beanDefinition == null){
           throw new BeansException("No bean named '" + beanName + "' is defined");
       }
       return  beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return  beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
