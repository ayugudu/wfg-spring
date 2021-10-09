package org.example.aop;

/**
 * 类级别的拦截
 */
public interface ClassFilter {


  boolean matches(Class<?> clazz);

}
