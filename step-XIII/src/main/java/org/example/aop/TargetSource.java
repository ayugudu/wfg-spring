package org.example.aop;

/**
 * 目标对象
 */
public class TargetSource {

   private final Object clazz;


    public TargetSource(Object clazz) {
        this.clazz = clazz;
    }

    public Class<?> [] getTargetClass(){

        return clazz.getClass().getInterfaces();
    }

    public Object getClazz() {
        return clazz;
    }
}
