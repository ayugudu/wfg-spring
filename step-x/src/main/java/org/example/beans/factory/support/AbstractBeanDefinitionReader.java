package org.example.beans.factory.support;

import org.example.core.io.DefaultResourceLoader;
import org.example.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements  BeanDefinitionReader {

    private final BeanDefinitionRegister register;


    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegister register) {
        this(register,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegister register,ResourceLoader resourceLoader){
        this.register =register;
        this.resourceLoader = resourceLoader;
    }

    public BeanDefinitionRegister getRegistry(){
        return  register;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
