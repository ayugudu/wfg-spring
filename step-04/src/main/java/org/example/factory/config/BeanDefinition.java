package org.example.factory.config;

import org.example.factory.PropertyValues;

/**
 * beandefinition 用于 存储需要构造的类
 */
public class BeanDefinition {

    /**
     * 类元信息
     */
    private Class beanClass;
    /**
     * 属性信息
     */
    private PropertyValues propertyValues;


    public BeanDefinition(Class beanClass){
        this.beanClass=beanClass;
    }

    public BeanDefinition(Class beanClass,PropertyValues propertyValues){
        this.beanClass=beanClass;
        this.propertyValues=propertyValues!=null ? propertyValues: new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
