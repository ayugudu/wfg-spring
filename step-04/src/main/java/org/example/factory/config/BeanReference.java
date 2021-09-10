package org.example.factory.config;

/**
 * 依赖对象的定义
 */
public class BeanReference {
  private final String name;


    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
