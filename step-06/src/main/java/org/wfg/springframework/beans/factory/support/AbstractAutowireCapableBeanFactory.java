package org.wfg.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.wfg.springframework.beans.BeansException;
import org.wfg.springframework.beans.PropertyValue;
import org.wfg.springframework.beans.PropertyValues;
import org.wfg.springframework.beans.factory.config.BeanDefinition;
import org.wfg.springframework.beans.factory.config.BeanPostProcessor;
import org.wfg.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * 实现 creteBean
 */
public abstract  class AbstractAutowireCapableBeanFactory  extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean =null;

        try{

            //创建bean
             bean = createBeanInstance(beanDefinition,beanName,args);
            //给bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
            //执行 bean的初始化方法和beanPostProcessor的前置和后置处理方法
            bean = initializeBean(beanName,bean,beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        //缓存
        addSingleton(beanName,bean);
        return  bean;
    }


    //创建bean
    protected  Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args){

        Constructor constructor =null;

        Class<?> beanClass= beanDefinition.getBeanClass();

        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for(Constructor con: declaredConstructors){
            if(null!= args && con.getParameterTypes().length==args.length){
                constructor=con;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition,constructor,args);
    }

    //为属性赋值
    protected  void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
       try{
           PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue  pro:  propertyValues.getPropertyValues()){
                String name= pro.getName();
                Object value=pro.getValue();
                if(value instanceof BeanReference){
                    BeanReference beanReference=(BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                //属性填充
                BeanUtil.setFieldValue(bean,name,value);

            }
       }catch (Exception e) {
           throw new BeansException("Error setting property values：" + e);
       }
    }
    //初始化QaQ
    private Object initializeBean(String beanName,Object bean,BeanDefinition beanDefinition){
     //1 执行 BeanPostProcessor before处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);
        // 待完成内容
        invokeInitMethods(beanName,wrappedBean,beanDefinition);
        //2 执行 BeanPostProcessor After 处理
        wrappedBean=applyBeanPostProcessorsAfterInitialization(bean,beanName);
        return  wrappedBean;
    }

    public void invokeInitMethods(String beanName,Object wrappedBean,BeanDefinition beanDefinition){

    }
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }


    public Object applyBeanPostProcessorsBeforeInitialization(Object bean,String beanName){
        Object result = bean;
        for(BeanPostProcessor processor : getBeanPostProcessorList() ){
            Object current = processor.postProcessBeforeInitialization(result,beanName);
            if(null == current){
                return  result;
            }
            result =current;
        }
        return  result;
    }

    public Object applyBeanPostProcessorsAfterInitialization(Object bean,String beanName){

        Object result= bean;
        for(BeanPostProcessor processor:getBeanPostProcessorList()){
            Object current = processor.postProcessAfterInitialization(result,beanName);
            if(null == current){
                return  result;
            }
            result = current;
        }
        return result;

    }



}
