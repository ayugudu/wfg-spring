package org.example.beans.factory;

public interface InitializingBean {
    /**
     * bean 处理属性填充后调用
     */

     void afterPropertiesSet();

}
