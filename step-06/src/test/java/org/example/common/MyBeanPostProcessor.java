package org.example.common;

import org.example.bean.UserService;
import org.wfg.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if("userService".equals(beanName)){
            UserService userService = (UserService) bean;
            userService.setLocation("改为：henan");
        }
        return  bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
       return  bean;
    }
}
