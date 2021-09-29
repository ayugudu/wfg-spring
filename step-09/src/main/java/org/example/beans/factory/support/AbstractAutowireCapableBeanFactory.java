package org.example.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.example.beans.BeansException;
import org.example.beans.PropertyValue;
import org.example.beans.PropertyValues;
import org.example.beans.factory.*;
import org.example.beans.factory.config.AutowireCapableBeanFactory;
import org.example.beans.factory.config.BeanDefinition;
import org.example.beans.factory.config.BeanPostProcessor;
import org.example.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public abstract class AbstractAutowireCapableBeanFactory  extends  AbstractBeanFactory implements AutowireCapableBeanFactory {

 private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();



 protected  Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args){
  Object bean =null;
  try{
   bean = createBeanInstance(beanDefinition,beanName,args);

   applyPropertyValues(beanName,bean,beanDefinition);

   bean = initializeBean(beanName,bean,beanDefinition);

  }catch (Exception e) {
      throw new BeansException("Instantiation of bean failed", e);
  }
  registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);
  if(beanDefinition.isSingleton()){
   addSingleton(beanName,bean);
  }
  return  bean;
 }
 protected  void registerDisposableBeanIfNecessary(String beanName,Object bean,BeanDefinition beanDefinition){
      if(!beanDefinition.isSingleton())return;


      if(bean instanceof  DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
          registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
      }


 }

 protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args){
  Constructor constructor =null;
  Class<?> beanClass = beanDefinition.getBeanClass();
  Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
  for(Constructor ctor : declaredConstructors){
   if(null!=args &&ctor.getParameterTypes().length==args.length){
      constructor =ctor;
      break;
   }

  }
  return getInstantiationStrategy().instantiate(beanDefinition,beanName,constructor,args);


 }

 public InstantiationStrategy getInstantiationStrategy() {
  return instantiationStrategy;
 }

 /**
  * 属性填充
  */

 protected  void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
  try{
    PropertyValues propertyValues = beanDefinition.getPropertyValues();
    for(PropertyValue pro : propertyValues.getPropertyValues()){
           String name = pro.getName();
           Object value = pro.getValue();
           if(value instanceof BeanReference){
              BeanReference beanReference = (BeanReference) value;
              value = getBean(beanReference.getBeanName());
           }
        BeanUtil.setFieldValue(bean,name,value);
    }

  }catch (Exception e) {
      throw new BeansException("Error setting property values：" + beanName);
  }



 }


 public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy){
     this.instantiationStrategy =instantiationStrategy;
 }
    /**
     * 初始化 方法
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @return
     */
 private Object initializeBean(String beanName,Object bean,BeanDefinition beanDefinition){
   //
     if(bean instanceof Aware){
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }

        if(bean instanceof BeanClassLoaderAware){
            ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
        }
        if(bean instanceof BeanNameAware){
            ((BeanNameAware) bean).setBeanName(beanName);
        }
     }
        // 执行 BeanPostProcessor 处理
         Object wrappedBean =applyBeanPostProcessorsBeforeInitialization(bean,beanName);

        // 执行bean对象的初始化方法
         try{
             invokeInitMethods(beanName,wrappedBean,beanDefinition);
         }catch (Exception e){
             throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
         }

         wrappedBean = applyBeanPostProcessorsAfterInitialization(bean,beanName);
         return  wrappedBean;


 }
    private void invokeInitMethods(String beanName,Object bean,BeanDefinition beanDefinition) throws Exception{
       // 实现接口 InitializingBean
        if(bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 注解配置
        String initMethodName= beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if(null == initMethod){
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }

    }

    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName){
     Object result =existingBean;
     for(BeanPostProcessor processor :getBeanPostProcessorList()){
         Object current = processor.postProcessBeforeInitialization(result,beanName);
         if(null == current){
             return  result;
         }
         result = current;

     }

     return  result;
    }

    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName){
     Object result = existingBean;
     for(BeanPostProcessor processor :getBeanPostProcessorList()){
         Object current = processor.postProcessAfterInitialization(result,beanName);
         if(null ==current){
             return  result;
         }

     }
     return  result;
    }

}
