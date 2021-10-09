package org.example.aop;

import org.aopalliance.aop.Advice;

/**
 * 切面接口 拥有一个切点 pointcut 和 advice 切面逻辑
 */
public interface Advisor {

    Advice getAdvice();
}
