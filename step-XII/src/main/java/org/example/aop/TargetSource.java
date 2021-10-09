package org.example.aop;


/**
 * 原目标对象
 */
public class TargetSource {

    private final Object target;


    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * 获取目标对象所实现的接口
     */
    public Class<?>[] getTargetClass(){
        return  this.target.getClass().getInterfaces();
    }


    public Object getTarget() {
        return target;
    }
}
