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

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

   private InstantiationStrategy instantiationStrategy = new CglibSubsClassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try{
            bean = createBeanInstance(beanDefinition,beanName,args);
            // 给bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
            //执行bean的初始化方法和BeanPsotProcessor
          bean = initializeBean(beanName,bean,beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);

        if(beanDefinition.isSingleton()){
            registerSingleton(beanName,bean);
        }
        return  bean;
    }


   protected void  registerDisposableBeanIfNecessary(String beanName,Object bean,BeanDefinition beanDefinition){
       if(!beanDefinition.isSingleton()){return;}

        if(bean instanceof DisposableBean ||StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName()) ){
            registerDisposableBean(beanName,new DisposableBeanAdapter(bean,beanName,beanDefinition));
        }
   }



    protected Object createBeanInstance(BeanDefinition beanDefinition ,String beanName,Object[] args){
        Constructor constructor = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for(Constructor ctor:declaredConstructors){
            if(null!= args && ctor.getParameterTypes().length==args.length){
                constructor = ctor;
            }
            break;
        }
        return  getInstantiationStrategy().instantiate(beanDefinition,beanName,constructor,args);
    }



    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue propertyValue: propertyValues.getPropertyValues()){
                String name= propertyValue.getName();
                Object value = propertyValue.getValue();
                if(value instanceof BeanReference){
                 BeanReference beanReference = (BeanReference) value;
                 value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean,name,value);


            }

        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }

    }
   private Object initializeBean(String beanName,Object bean,BeanDefinition beanDefinition) throws Exception {

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

        // 执行 beanPostProcessor
       Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);

        // 执行对象初始化操作
       try{
           invokeInitMethods(beanName, bean, beanDefinition);
       } catch (Exception e) {
           throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
       }
      wrappedBean = applyBeanPostProcessorsAfterInitialization(bean,beanName);

    return  wrappedBean;
   }


   private void invokeInitMethods(String beanName,Object bean,BeanDefinition beanDefinition) throws Exception{
        // 提供了接口调用
        if(bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }
        //注解配置 init-method

       String initMethodName = beanDefinition.getInitMethodName();

        if(StrUtil.isNotEmpty(initMethodName)){
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if(null == initMethod){
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }


   }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existing, String beanName) throws Exception {
       Object result =existing;
       for(BeanPostProcessor beanPostProcessor: getBeanPostProcessorList()){
           Object object = beanPostProcessor.postProcessBeforeInitialization(result,beanName);
           if(null==object){
               return  result;
           }
           result =object;
       }
       return  result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
       Object result =existingBean;
       for(BeanPostProcessor processor: getBeanPostProcessorList()){
           Object object = processor.postProcessAfterInitialization(result,beanName);
           if(null==object){
               return object;
           }
           result=object;
       }
       return  result;
    }



    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
