package wfg.springframework;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean对象工厂，存放bean定义到map中以及获取
 */
public class BeanFactory {
    private Map<String,BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<>();

    public  Object  getBean(String name){
     return  beanDefinitionMap.get(name).getBean();
    }

    public void setBeanDefinition(String name,BeanDefinition beanDefinition){
        beanDefinitionMap.put(name,beanDefinition);
    }
}
