package org.example.aop.framework;

import org.example.aop.AdvisedSupport;

/**
 * 织入器 返回织入了横切逻辑的目标对象的代理对象
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;


    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {

        return createAopProxy().getProxy();

    }

    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }

     return  new JdkDynamicAopProxy(advisedSupport);
    }
}