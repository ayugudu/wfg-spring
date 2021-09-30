package org.example.beans.factory;

public interface BeanClassLoaderAware extends  Aware{
    void setBeanClassLoader(ClassLoader loader);
}
