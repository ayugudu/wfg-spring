package org.example.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.example.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements  InstantiationStrategy{


    @Override
    public Object instantiate(BeanDefinition beanDefinition, String name,Constructor ctor,Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();

            }
        });
        if(null == ctor){return  enhancer.create();}
       return enhancer.create(ctor.getParameterTypes(),args);
    }
}
