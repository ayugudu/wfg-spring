package org.example.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import org.example.beans.BeansException;
import org.example.beans.factory.DisposableBean;
import org.example.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

public class DisposableBeanAdapter implements DisposableBean {

   private final Object bean;

   private final String beanName;

   private String  destroyMethodName;

    public DisposableBeanAdapter(Object bean, String name, BeanDefinition beanDefinition) {
        this.bean = bean;
        beanName = name;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy()throws Exception {
    // 实现接口 DisposableBean
        if(bean instanceof  DisposableBean){
            ((DisposableBean) bean).destroy();
        }

        // 注解配置destroy-method
        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))){
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if(null== destroyMethod){
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }

    }
}
