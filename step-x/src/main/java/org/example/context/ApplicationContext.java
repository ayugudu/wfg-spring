package org.example.context;

import org.example.beans.factory.HierarchicalBeanFactory;
import org.example.beans.factory.ListableBeanFactory;
import org.example.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader,ApplicationEventPublisher {
}
