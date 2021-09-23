package spring.beans.factory.support;

import spring.beans.factory.config.BeanDefinition;
import spring.beans.factory.config.BeanPostProcessor;
import spring.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

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
       public <T> T getBean(String beanName, Class<T> requireType) {
              return (T) getBean(beanName);
       }
       protected<T> T doGetBean(final String name,final Object[] args){
              Object bean = getSingleton(name);
              if(null!=bean){
                    return (T) bean;
              }
              BeanDefinition beanDefinition = getBeanDefinition(name);
              return (T) createBean(name,beanDefinition,args);
       }

       protected abstract BeanDefinition getBeanDefinition(String beanName);


       protected  abstract  Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args);

       /**
        * 更新list
        * @param beanPostProcessor
        */
       public void addBeanPostProcessors(BeanPostProcessor beanPostProcessor){
              this.beanPostProcessorList.remove(beanPostProcessor);
              this.beanPostProcessorList.add(beanPostProcessor);
       }
       public List<BeanPostProcessor> getBeanPostProcessorList() {return  this.beanPostProcessorList;}


}
