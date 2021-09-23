package spring.beans.factory.support;

import spring.core.io.Resource;
import spring.core.io.ResourceLoader;

/**
 * 定义读取 beanDefinition到容器里的行为
 */
public interface BeanDefinitionReader {
     BeanDefinitionRegistry getRegistry();

     ResourceLoader getResourceLoader();

      void loadBeanDefinitions(Resource resource);


      void loadBeanDefinitions(Resource... resources);

      void loadBeanDefinitions(String location);

      void  loadBeanDefinitions(String... locations);





}
