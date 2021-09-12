package org.wfg.springframework.beans.factory.config;

public class BeanReference {

private final String beanName;

public BeanReference(String name) {
        this.beanName = name;
    }

    public String getBeanName() {
        return beanName;
    }
}
