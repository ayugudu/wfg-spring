package org.example.aop;

/**
 * 切点 设置拦截级别（类和方法）
 */
public interface Pointcut {

    ClassFilter getClassFilter();


    MethodMatcher getMethodMatcher();
}
