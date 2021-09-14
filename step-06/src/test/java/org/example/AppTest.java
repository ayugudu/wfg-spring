package org.example;

import static org.junit.Assert.assertTrue;

import org.example.bean.UserService;
import org.junit.Test;
import org.wfg.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {
       //1 初始化BeanFactory
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");

       //2 获取bean对象调用方法

        UserService userService = context.getBean("userService",UserService.class);

        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);

    }
}
