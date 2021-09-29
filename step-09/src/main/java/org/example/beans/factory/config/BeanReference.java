package org.example.beans.factory.config;

public class BeanReference {
    private final String beanName;


    public BeanReference(String name) {
        beanName = name;
    }

    public String getBeanName() {
        return beanName;
    }
}
