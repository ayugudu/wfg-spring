package org.wfg.springframework.beans.factory;

/**
 * 容器接口
 */
public interface BeanFactory {

    /**
     * 获取无参对象
     * @param name
     * @return
     */
    Object getBean(String name);

    /**
     * 获取有参对象
     * @param name
     * @param args
     * @return
     */
    Object getBean(String name, Object...args);



    <T> T getBean(String name,Class<T> requiredTYpe );

}
