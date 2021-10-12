package org.example.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.example.aop.MethodBeforeAdvice;

/**
 * 前置方法的实现
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor(){

    }

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice){
        this.advice =advice;
    }


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        this.advice.before(invocation.getMethod(),invocation.getArguments(),invocation.getThis());

        return  invocation.proceed();
    }
}
