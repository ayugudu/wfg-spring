package org.example;

import static org.junit.Assert.assertTrue;

import org.example.beans_x.CustomEvent;
import org.example.context.support.ClassPathXmlApplicationContext;
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
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));
        applicationContext.getBean("c1");
        applicationContext.registerShutdownHook();
    }

}
