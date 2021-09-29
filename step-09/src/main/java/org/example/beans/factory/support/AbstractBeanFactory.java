package org.example.beans.factory.support;

import org.example.beans.factory.FactoryBean;
import org.example.beans.factory.config.BeanDefinition;
import org.example.beans.factory.config.BeanPostProcessor;
import org.example.beans.factory.config.ConfigurableBeanFactory;
import org.example.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 工厂扩展性 与 获取 bean
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

   private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

   private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();



   public Object getBean(String name){
      return  doGetBean(name,null);

   }



   public Object getBean(String name,Object...args){
      return  doGetBean(name,args);
   }


   public <T> T getBean(String name ,Class<T> requiredType){
         return (T) getBean(name);
   }


   protected  <T> T doGetBean(String name,Object[] args){

      Object sharedInstance = getSingleton(name);

      if(sharedInstance !=null){
         return (T) getObjectForBeanInstance(sharedInstance,name);
      }
      BeanDefinition beanDefinition = getBeanDefinition(name);
      Object bean = createBean(name,beanDefinition,args);

      return (T) getObjectForBeanInstance(bean,name);

   }

   private Object getObjectForBeanInstance(Object beanInstance,String beanName){
      if(!(beanInstance instanceof FactoryBean)){
         return  beanInstance;
      }
      Object object = getCachedObjectForFactoryBean(beanName);

      if(object==null){
         FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
         object = getObjectFromFactoryBean(factoryBean,beanName);
      }
      return  object;
   }

   protected  abstract  BeanDefinition getBeanDefinition(String beanName);

   protected  abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args);

   public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
      beanPostProcessorList.remove(beanPostProcessor);
      beanPostProcessorList.add(beanPostProcessor);
   }



   public List<BeanPostProcessor> getBeanPostProcessorList(){
      return  beanPostProcessorList;
   }


   public ClassLoader getBeanClassLoader() {
      return beanClassLoader;
   }
}
