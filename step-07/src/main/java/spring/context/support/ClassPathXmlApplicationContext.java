package spring.context.support;

import spring.beans.BeansException;
import spring.beans.factory.ConfigurableListableBeanFactory;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

   private String[] configLocations;


    public ClassPathXmlApplicationContext(String configLocations){
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
