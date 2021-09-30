package org.example.beans.factory;

public interface InitializingBean {

    /**
     * bean 处理了属性填充后调用
     *
     */

    void afterPropertiesSet();

}
