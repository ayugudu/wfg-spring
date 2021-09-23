package spring.beans.factory.config;

import spring.beans.PropertyValues;

/**
 * bean的信息存储
 */
public class BeanDefinition {

  private final Class beanClass;

  private final PropertyValues propertyValues;

    /**
     * 定义初始化操作
     */
  private String initMethodName;

    /**
     * 定义结束操作
     */
  private String destroyMethodName;


    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues!=null ? propertyValues : new PropertyValues();
    }


    public BeanDefinition(Class beanClass){
        this.beanClass=beanClass;
        propertyValues=new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
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
