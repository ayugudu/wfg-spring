package spring.beans.factory;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{
    /**
     * 按照类型返回bean 实力
     */

        <T> Map<String,T> getBeansOfType(Class<T> type);

    /**
     * 返回注册表中的所有bean名称
     *
     */
    String [] getBeanDefinitionNames();
}
