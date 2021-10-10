package org.example.context;

import org.example.beans.factory.HierarchicalBeanFactory;
import org.example.beans.factory.ListableBeanFactory;
import org.example.core.io.Resource;
import org.example.core.io.ResourceLoader;

/**
 * 流程控制（spring 容器）
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {



}
