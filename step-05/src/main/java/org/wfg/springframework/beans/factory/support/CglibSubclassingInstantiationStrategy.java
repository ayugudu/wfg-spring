package org.wfg.springframework.beans.factory.support;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.wfg.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements  InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor con, Object[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        if(null == con){return  enhancer.create();}
        return  enhancer.create(con.getParameterTypes(),args);
    }
}
