package spring.beans.factory.config;

/**
 * 单列注册行为
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
