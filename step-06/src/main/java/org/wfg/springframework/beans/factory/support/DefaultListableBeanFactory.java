package org.wfg.springframework.beans.factory.support;

import org.wfg.springframework.beans.BeansException;
import org.wfg.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.wfg.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory  extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
   private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
               beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
       BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanDefinition) {
        return beanDefinitionMap.containsKey(beanDefinition);
    }

    @Override
    public String[] getBeanDefinitions() {
        return new String[0];
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
       final Map<String,T> result =new HashMap<>();
       beanDefinitionMap.forEach( (beanName,beanDefinition) -> {
           Class beanClass = beanDefinition.getBeanClass();
           if(type.isAssignableFrom(beanClass)){
               result.put(beanName, (T) getBean(beanName));
           }
       });
       return  result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }
}
