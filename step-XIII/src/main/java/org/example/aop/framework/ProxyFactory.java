package org.example.aop.framework;

import org.example.aop.AdvisedSupport;

/**
 * 代理工厂，获取代理实现
 */
public class ProxyFactory {
    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport){
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy(){
        return
    }


    private AopProxy createAopProxy(){
        if(advisedSupport.isProxyTargetClass()){
            return  new C
        }
    }


}
