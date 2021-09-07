package org.example.factory.config;

/**
 *  beandefinition 将只保存类的信息，而不存储实力化后的信息
 */
public class BeanDefinition {
    private Class beanClass;
    public BeanDefinition(Class beanClass){
        this.beanClass=beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }
}
