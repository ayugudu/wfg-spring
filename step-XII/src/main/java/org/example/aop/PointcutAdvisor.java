package org.example.aop;

/**
 * 切面的基础型
 */
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();


}
