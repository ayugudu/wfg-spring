package org.example.beans.factory.config;

import org.example.beans.factory.PropertyValues;

/**
 * 用于 存储class的信息以及属性信息
 */
public class BeanDefinition {

    private   Class clazz;

    private PropertyValues  propertyValues;

    /**
     * 初始化
     */
    private String initMethodName;

    /**
     * 销毁
     */
    private String destroyMethodName;

    public BeanDefinition(Class clazz) {
        this.clazz = clazz;
        this.propertyValues= new PropertyValues();
    }

    public BeanDefinition(Class clazz,PropertyValues propertyValues)
    {
        this.clazz=clazz;
        this.propertyValues=propertyValues;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }
}
