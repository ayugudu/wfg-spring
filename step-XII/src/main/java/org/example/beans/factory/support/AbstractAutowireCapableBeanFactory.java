package org.example.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import org.example.beans.BeansException;
import org.example.beans.PropertyValue;
import org.example.beans.PropertyValues;
import org.example.beans.factory.*;
import org.example.beans.factory.config.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public abstract class AbstractAutowireCapableBeanFactory extends  AbstractBeanFactory implements AutowireCapableBeanFactory {


    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();


    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try{
            //判断是否返回bean代理对象
            bean = resolveBeforeInstantiation(beanName,beanDefinition);
            if(null!=bean){
                return  bean;
            }
            //实例化
            bean = createBeanInstance(beanDefinition,beanName,args);

            //给bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);

            //执行bean的初始化方法
            bean = initializeBean(beanName,bean,beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed", e);
        }
        // 注册实现 DisposableBean 接口的bean 对象
        registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);

        if(beanDefinition.isSingleton()){
            registerSingleton(beanName,bean);
        }
        return  bean;
    }



    protected  void registerDisposableBeanIfNecessary(String beanName,Object bean,BeanDefinition beanDefinition){
        if(!beanDefinition.isSingleton()){
            return;
        }
        if(bean instanceof  DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
        }

    }

     protected  void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
       try{  PropertyValues propertyValues = beanDefinition.getPropertyValues();
         for(PropertyValue propertyValue : propertyValues.getPropertyValues()){
             String name  =propertyValue.getName();
             Object value =propertyValue.getValue();

             if(value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                   value = getBean(beanReference.getBeanName());
             }
              BeanUtil.setFieldValue(bean,name,value);
         }
       }
       catch (Exception e) {
           throw new BeansException("Error setting property values：" + beanName);
       }


     }

     private Object initializeBean(String beanName,Object bean,BeanDefinition beanDefinition){
        if(bean instanceof Aware){
            if(bean instanceof BeanFactoryAware){
                 ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if(bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanCLassLoader(getBeanClassLoader());
            }
            if(bean instanceof BeanNameAware){
                 ((BeanNameAware) bean).setBeanName(beanName);
            }
        }
           Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);

           // 执行bean 对象初始化方法
         try {
             invokeInitMethods(beanName,wrappedBean,beanDefinition);
         } catch (Exception e) {
             e.printStackTrace();
         }

         wrappedBean =applyBeanPostProcessorsAfterInitialization(bean,beanName);
             return  wrappedBean;
     }

     private void invokeInitMethods(String beanName,Object bean,BeanDefinition beanDefinition) throws  Exception{
        if(bean instanceof  InitializingBean){
              ((InitializingBean) bean).afterPropertiesSet();
        }
        //注解配置 init-method
         String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method  initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if(null == initMethod){
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
     }

    protected  Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args){
        Constructor constructor =null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?> [] declaredConstructors = beanClass.getDeclaredConstructors();
        for(Constructor constructor1:declaredConstructors){
            if(null!=args&&constructor1.getParameterTypes().length==args.length){
                constructor = constructor1;
                break;
            }
        }
        return  getInstantiationStrategy().instantiate(beanDefinition,beanName,constructor,args);
    }

    protected Object resolveBeforeInstantiation(String beanName,BeanDefinition beanDefinition){
        Object bean = applyBeanPostProcessorBeforeInstantiation(beanDefinition.getBeanClass(),beanName);
        if(null!= bean){
            bean  =applyBeanPostProcessorsAfterInitialization(bean,beanName);
        }
         return  bean;
    }

    protected  Object applyBeanPostProcessorBeforeInstantiation(Class<?> beanClass,String beanName){
        for(BeanPostProcessor beanPostProcessor:getBeanPostProcessorList()){
            if(beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                Object result =   ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
                return result;
             }
        }
        return  null;
    }

    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String beanName){
        Object result = existingBean;
        for(BeanPostProcessor processor: getBeanPostProcessorList()){
            Object current = processor.PostProcessBeforeInitialization(result,beanName);
            if(current == null){
                return  result;
            }
            result =current;

        }
        return result;

    }

    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean,String beanName){
        Object result = existingBean;
        for(BeanPostProcessor  processor: getBeanPostProcessorList() ){
            Object object = processor.postProcessAfterInitialization(result,beanName);
            if(null==object){
                return  result;
            }
            result =object;
        }
        return  result;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
