package org.example.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import org.example.beans.BeansException;
import org.example.beans.factory.DisposableBean;
import org.example.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 适配器模式 将反射的销毁转换为接口类型销毁
 */
public class DisposableBeanAdapter implements DisposableBean {
   private final Object bean;
   private  final String beanName;
   private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String name, BeanDefinition beanDefinition) {
        this.bean = bean;
        beanName = name;
        this.destroyMethodName =beanDefinition.getDestroyMethodName();
    }


    @Override
    public void destroy() throws Exception{

        //1 接口
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        //2 注解配置

        if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof  DisposableBean && "destroy".equals(this.destroyMethodName))){
            Method method = bean.getClass().getMethod(destroyMethodName);
            if(null==method){
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            method.invoke(bean);
        }

    }
}
