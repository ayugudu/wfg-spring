package org.example;

import static org.junit.Assert.assertTrue;

import org.example.aop.AdvisedSupport;
import org.example.aop.TargetSource;
import org.example.aop.aspect.AspectJExpressionPointcut;
import org.example.beanXII.IUserService;
import org.example.beanXII.UserService;
import org.example.beanXII.UserServiceInterceptor;
import org.example.context.support.ClassPathXmlApplicationContext;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    private AdvisedSupport advisedSupport;

    @Before
    public void init() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* org.example.beanXII.IUserService.*(..))"));
    }
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
