package org.example.aop;

public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();

}
