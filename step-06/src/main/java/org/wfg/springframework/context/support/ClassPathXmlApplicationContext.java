package org.wfg.springframework.context.support;

public class ClassPathXmlApplicationContext extends  AbstractXmlApplicationContext{
    private String[] configLocations;


    public ClassPathXmlApplicationContext(){}


    public ClassPathXmlApplicationContext(String configLocations) throws Exception {
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) throws Exception {
        this.configLocations=configLocations;
        refresh();
    }
    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
