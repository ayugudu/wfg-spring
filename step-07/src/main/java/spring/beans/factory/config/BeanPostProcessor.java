package spring.beans.factory.config;

public interface BeanPostProcessor {

    /**
     * 在对象初始化之前，进行扩展
     * @return
     */
  Object  postProcessBeforeInitialization(Object bean,String beanName);

/**
 * 在对象初始化之后，进行扩展
 */

  Object postProcessAfterInitialization(Object bean,String beanName);


}
