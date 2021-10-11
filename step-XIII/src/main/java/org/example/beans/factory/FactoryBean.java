package org.example.beans.factory;

public interface FactoryBean<T> {

    T getObject();

    Class<?> getObjectType();

    boolean isSingleton();


}
