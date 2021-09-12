package org.wfg.springframework.beans.factory.support;


import cn.hutool.core.bean.BeanUtil;


import org.wfg.springframework.PropertyValue;
import org.wfg.springframework.PropertyValues;
import org.wfg.springframework.beans.factory.config.BeanDefinition;
import org.wfg.springframework.beans.factory.config.BeanReference;


import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();


    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
      // 创建bean 实例化
       Object bean  = createBeanInstance(beanName,beanDefinition,args);
      
      // 为属性赋值
       applyPropertyValues(beanName,beanDefinition,bean);
      //将bean 添加到 缓存中
       addSingleton(beanName,bean);

       return bean;
    }

    protected  Object createBeanInstance(String beanName ,BeanDefinition beanDefinition,Object[] args){
        Constructor constructor =null;
        Class clazz= beanDefinition.getBeanClass();
        Constructor[] cons = clazz.getDeclaredConstructors();
        for(Constructor con : cons){
            if (args!=null&&con.getParameterTypes().length==args.length) {
             constructor = con;
             break;
            }
            }

        return instantiationStrategy.instantiate(beanDefinition,beanName,constructor,args) ;
    }

     protected void applyPropertyValues(String beanName ,BeanDefinition beanDefinition,Object bean){
         PropertyValues propertyValues = beanDefinition.getPropertyValues();

         PropertyValue[] pros = propertyValues.getPropertyValues();

         for(PropertyValue pro : pros){
             String name =pro.getName();
             Object value = pro.getValue();
             if (value instanceof BeanReference) {
                 BeanReference beanReference = (BeanReference) value;
                 value =getBean(beanReference.getBeanName());
             }
             BeanUtil.setFieldValue(bean,name,value);

             }

         }




     }



