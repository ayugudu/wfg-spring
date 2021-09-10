package org.example;

import static org.junit.Assert.assertTrue;

import org.example.factory.PropertyValue;
import org.example.factory.PropertyValues;
import org.example.factory.config.BeanDefinition;
import org.example.factory.config.BeanReference;
import org.example.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        //初始化 beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 注册 userDao bean
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));
        //设置userService属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("id","1"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));

        //注册 userServiceBean
        beanFactory.registerBeanDefinition("userService",new BeanDefinition(UserService.class,propertyValues));

        //userService获取bean
        UserDao userDao = (UserDao) beanFactory.getBean("userDao");
        System.out.println(userDao);


        UserService userService = (UserService) beanFactory.getBean("userService");

        System.out.println(userService.getId());


        userService.queryUserInfo();
    }
}
