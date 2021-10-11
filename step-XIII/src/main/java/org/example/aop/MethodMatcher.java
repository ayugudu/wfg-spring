package org.example.aop;

import java.lang.reflect.Method;

/**
 * 方法级别器诶单
 */
public interface MethodMatcher {
    boolean matches(Method method, Class<?> targetClass);

}
