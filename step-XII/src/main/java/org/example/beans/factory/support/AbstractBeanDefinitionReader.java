package org.example.beans.factory.support;

import org.example.core.io.DefaultResourceLoader;
import org.example.core.io.ResourceLoader;

public abstract  class AbstractBeanDefinitionReader implements BeanDefinitionReader{
   private final BeanDefinitionRegistry registry;

   private ResourceLoader resourceLoader;

   protected  AbstractBeanDefinitionReader(BeanDefinitionRegistry registry){
       this(registry,new DefaultResourceLoader());
   }

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
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
