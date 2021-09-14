package org.wfg.springframework.beans.factory.config;

/**
 * 定义引用属性
 */
public class BeanReference {

  private final String beanName;


  public BeanReference(String beanName) {
    this.beanName = beanName;
  }

  public String getBeanName() {
    return beanName;
  }
}
