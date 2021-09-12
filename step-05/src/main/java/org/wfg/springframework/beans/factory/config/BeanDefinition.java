package org.wfg.springframework.beans.factory.config;

import org.wfg.springframework.PropertyValues;

/**
 * 存储一个类的基本信息
 */
public class BeanDefinition {

 private Class beanClass;

 private PropertyValues propertyValues;

 public BeanDefinition(Class beanClass){
   this.beanClass=beanClass;
   propertyValues=new PropertyValues();
 }

 public BeanDefinition(Class beanClass,PropertyValues propertyValues){
  this.beanClass=beanClass;
  this.propertyValues=propertyValues;
 }

 public Class getBeanClass() {
  return beanClass;
 }

 public PropertyValues getPropertyValues() {
  return propertyValues;
 }
}
