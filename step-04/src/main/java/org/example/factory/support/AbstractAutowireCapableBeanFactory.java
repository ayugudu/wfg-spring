package org.example.factory.support;


import cn.hutool.core.bean.BeanUtil;
import org.example.factory.BaseException;
import org.example.factory.PropertyValue;
import org.example.factory.PropertyValues;
import org.example.factory.config.BeanDefinition;
import org.example.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * 实现createBean 方法
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();


    @Override
    public Object createBean(BeanDefinition beanDefinition, String name, Object[] args) {
        Object bean = null;
        //创建bean
         bean = createBeanInstance(beanDefinition,name,args);

         if(beanDefinition.getPropertyValues()!=null) {
             //给bean 设置属性
             applyPropertyValues(name, bean, beanDefinition);
         }

         //设置bean的缓存
         addSingle(name,bean);
         return  bean;

    }

    protected  Object createBeanInstance(BeanDefinition beanDefinition,String name,Object [] args){
        Constructor constructorToUse =null;

        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] constructors = beanClass.getDeclaredConstructors();
        for(Constructor con: constructors ){
            if(null != args&&args.length==con.getParameterTypes().length ){
                constructorToUse= con;
                break;
            }
        }
       return  getInstantiationStrategy().instantiate(beanDefinition,name,constructorToUse,args);
    }

    /**
     * bean 属性填充
     * @return
     */
    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
        try{
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for(PropertyValue propertyValue: propertyValues.getPropertyValues()){
            String name= propertyValue.getName();
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference){
                //A 依赖B ，获取B的实力化
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }
            BeanUtil.setFieldValue(bean,name,value);
    }
        }catch (Exception e){
            throw new BaseException("Error setting property values：" + beanName);
        }
    }




    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }


}
