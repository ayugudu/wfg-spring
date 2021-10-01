package org.example.beans.factory.support;

import org.example.beans.BeansException;
import org.example.beans.factory.config.BeanDefinition;
import org.example.core.io.Resource;
import org.example.core.io.ResourceLoader;

public interface BeanDefinitionReader {
    BeanDefinitionRegister getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;





}
