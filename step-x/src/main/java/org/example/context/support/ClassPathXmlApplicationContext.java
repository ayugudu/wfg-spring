package org.example.context.support;

import org.example.beans.BeansException;

import static java.time.zone.ZoneRulesProvider.refresh;

public class ClassPathXmlApplicationContext extends  AbstractXmlApplicationContext{

    private String[] configLocations;
    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     *
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }


    protected String[] getConfigLocations() {
        return configLocations;
    }
}
