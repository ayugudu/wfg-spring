package org.example;

import static org.junit.Assert.assertTrue;

import org.example.factory.config.BeanDefinition;
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
       //1 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2 注入 bean
        BeanDefinition beanDefinition = new BeanDefinition(User.class);
        beanFactory.registerBeanDefinition("user",beanDefinition);

        //4 获取bean
        User user = (User) beanFactory.getBean("user",1);
        user.test();
    }
}
