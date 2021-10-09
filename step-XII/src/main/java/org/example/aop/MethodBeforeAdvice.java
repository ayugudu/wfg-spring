package org.example.aop;

import java.lang.reflect.Method;

public interface MethodBeforeAdvice  extends BeforeAdvice{

    Object before(Method method, Object[] args , Object target);
}
