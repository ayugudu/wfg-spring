package org.example.aop;

public interface Pointcut {
    /**
     * 返回此切入点的 classFilter
     * @return
     */
    ClassFilter getClassFilter();
    /**
     * 返回此切入点的 MethodMatcher
     */
    MethodMatcher getMethodMatcher();

}
