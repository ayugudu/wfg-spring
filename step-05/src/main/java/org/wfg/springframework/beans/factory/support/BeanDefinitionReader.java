package org.wfg.springframework.beans.factory.support;

import org.wfg.springframework.core.io.Resource;
import org.wfg.springframework.core.io.ResourceLoader;

/**
 * 获取 文件信息
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource);

     void loadBeanDefinitions(Resource... resources);

     void loadBeanDefinitions(String location);
}
