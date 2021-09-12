package org.wfg.springframework.beans.factory.support;

import org.wfg.springframework.core.io.DefaultResourceLoader;
import org.wfg.springframework.core.io.Resource;
import org.wfg.springframework.core.io.ResourceLoader;

public abstract class  AbstractBeanDefinitionReader implements  BeanDefinitionReader{

    private final BeanDefinitionRegistry registry;

   private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry,ResourceLoader resourceLoader){
        this.registry=registry;
        this.resourceLoader=resourceLoader;

    }


    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }


}
