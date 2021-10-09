package org.example.beans.factory;

public interface BeanClassLoaderAware extends Aware{

  void setBeanCLassLoader(ClassLoader cLassLoader);
}
