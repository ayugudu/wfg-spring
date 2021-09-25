package org.example.context.support;

import org.example.beans.BeansException;

public class ClassPathXmlApplicationContext extends  AbstractXmlApplicationContext{

    private String[] configLocations;



    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException{
        this.configLocations=configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
