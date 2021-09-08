package org.example.factory.config;

/**
 * 用于保存class 信息
 */
public class BeanDefinition {
    private Class beanClass;
    public  BeanDefinition(Class bean){
         this.beanClass=bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }
}
