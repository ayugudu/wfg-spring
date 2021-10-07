package org.example.aop;

public interface ClassFilter {

    /**
     * 切入点应用于给定的接口还是目标类
     * @return
     */
    boolean matches(Class<?> clazz);


}
