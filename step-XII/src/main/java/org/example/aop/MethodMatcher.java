package org.example.aop;

import java.lang.reflect.Method;

/**
 * 方法级别的拦截
 */
public interface MethodMatcher {
    boolean matches(Method method, Class<?> targetClass);
}
