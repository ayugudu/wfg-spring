package org.example.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {

    /**
     * 检查给定的方法是否匹配
     */
    boolean matches(Method method,Class<?> targetClass);
}
