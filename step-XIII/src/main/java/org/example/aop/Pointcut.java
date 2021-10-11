package org.example.aop;

public interface Pointcut {

   ClassFilter getClassFilter();

   MethodMatcher getMethodMatcher();


}
