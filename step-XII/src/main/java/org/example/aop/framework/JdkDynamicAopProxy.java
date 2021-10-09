package org.example.aop.framework;


import org.aopalliance.intercept.MethodInterceptor;
import org.example.aop.AdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk 代理的实现
 */
public class JdkDynamicAopProxy implements  AopProxy, InvocationHandler {

    private final AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
       if(advisedSupport.getMethodMatcher().matches(method,advisedSupport.getTargetSource().getTarget().getClass())){
           MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
           return  methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(),method,objects));
       }
       return  method.invoke(advisedSupport.getTargetSource().getTarget(),objects);
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advisedSupport.getTargetSource().getTargetClass(), this);
    }
}
