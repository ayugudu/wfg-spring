package org.example.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.example.beans.BeansException;
import org.example.beans.factory.*;
import org.example.beans.factory.config.BeanDefinition;
import org.example.beans.factory.config.BeanPostProcessor;
import org.example.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean=null;
        try{
            //创键bean
            bean = createBeanInstance(beanDefinition,beanName,args);
            //给bean属性赋值
            applyPropertyValues(beanName,bean,beanDefinition);
            // 执行bean的初始化方法 和 beanPostProcessor 的前置和后置方法
            bean = initializeBean(beanName,bean,beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed", e);
        }
        //注册 实现disposableBean接口的bean对象
       registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);
        // 注册bean
        addSingleton(beanName,bean);
        return  bean;
    }

    /**
     * 创键bean
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected  Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object [] args){
        Constructor constructor =null;
        Class<?> beanClass = beanDefinition.getClazz();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        for(Constructor ctor :declaredConstructors){
            if(null!=args && args.length==ctor.getParameterTypes().length){
                constructor =ctor;
                 break;
            }

        }

        return  getInstantiationStrategy().instantiate(beanDefinition,beanName,constructor,args);
    }

    /**
     * 为属性赋值
     * @return
     */

     protected  void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
         try{
             PropertyValues propertyValues = beanDefinition.getPropertyValues();
             for(PropertyValue pro : propertyValues.getPropertyValues()){
                 String name = pro.getValueName();
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



     protected void registerDisposableBeanIfNecessary(String beanName,Object bean,BeanDefinition beanDefinition){
         if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
             registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
         }
     }

    /**
     * 方法初始化
     * @return
     */

    private Object initializeBean(String beanName,Object bean,BeanDefinition beanDefinition){
          if(bean instanceof Aware){
              if(bean instanceof BeanFactoryAware){
                          ((BeanFactoryAware) bean).setBeanFactory(this);
              }
              if(bean instanceof BeanClassLoaderAware){
                   ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
              }
              if(bean instanceof  BeanNameAware){
                  ((BeanNameAware) bean).setBeanName(beanName);
              }
          }

          Object wrappedBean =applyBeanPostProcessorsBeforeInitialization(bean,beanName);

          //  执行bean对象的初始化方法
          try{
              invokeInitMethods(beanName, bean, beanDefinition);
          }catch (Exception e ){
              throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
          }

         wrappedBean =applyBeanPostProcessorsAfterInitialization(bean,beanName);
          return  wrappedBean;


    }


    private void invokeInitMethods(String beanName,Object bean,BeanDefinition beanDefinition) throws Exception {
        //实现接口执行
        if(bean instanceof InitializingBean){
             ((InitializingBean) bean).afterPropertiesSet();
        }

        // 注解配置 init-method 执行
        String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
             Method initMethod = beanDefinition.getClazz().getMethod(initMethodName);
             if(null==initMethod){
                 throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
             }
           initMethod.invoke(bean);
        }
    }


    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName){
        Object result =existingBean;
        for(BeanPostProcessor processor:getBeanPostProcessors()){
            Object current = processor.postProcessBeforeInitialization(existingBean,beanName);
            if(null==current){
                return  result;
            }
            result=current;
        }
        return  result;
    }

    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean ,String beanName){
        Object result = existingBean;
        for(BeanPostProcessor processor:getBeanPostProcessors()){
           Object current =processor.postProcessAfterInitialization(existingBean, beanName);
           if(current==null){return  result;}
           result =current;
        }
        return result;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
