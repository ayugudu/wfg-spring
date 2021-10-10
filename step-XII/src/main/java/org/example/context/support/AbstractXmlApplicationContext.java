package org.example.context.support;

import org.example.beans.factory.support.DefaultListableBeanFactory;
import org.example.beans.factory.xml.XmlBeanDefinitionReader;

public  abstract  class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory,this);
        String[] configLocations = getConfigLocations();
        if(null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }

    }

    protected abstract String[] getConfigLocations();
}
