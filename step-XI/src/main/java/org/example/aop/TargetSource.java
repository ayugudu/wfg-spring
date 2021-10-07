package org.example.aop;

public class TargetSource {
    private final Object target;


    public TargetSource(Object target){
        this.target=target;
    }

    /**
     * 返回由此targetSource返回的目标类型
     * @return
     */
    public Class<?>[] getTargetClass(){
        return  this.target.getClass().getInterfaces();
    }


    /**
     * 返回一个目标实例
     */
    public Object getTarget(){
        return  this.target;
    }
}
