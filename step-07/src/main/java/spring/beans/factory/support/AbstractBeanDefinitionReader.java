package spring.beans.factory.support;

import spring.core.io.DefaultResourceLoader;
import spring.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements  BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry,ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader=resourceLoader;
    }

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry){
        this(beanDefinitionRegistry,new DefaultResourceLoader());
    }

     public BeanDefinitionRegistry getRegistry(){
        return  registry;
     }

     public ResourceLoader getResourceLoader(){
        return  resourceLoader;
     }


}
