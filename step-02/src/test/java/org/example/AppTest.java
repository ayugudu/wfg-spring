package org.example;

import static org.junit.Assert.assertEquals;
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
        //初始化 beanFactory
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();

        //注册bean
        BeanDefinition beanDefinition = new BeanDefinition(User.class);
        beanFactory.registerBeanDefinition("user",beanDefinition);

        //获取bean
        User user1=(User)beanFactory.getBean("user");

        User user2 = (User) beanFactory.getBean("user");
        //测试是否相等
        assertEquals(user1,user2);

    }
}
