package org.example;

import org.example.bean.UserDao;
import org.example.bean.UserService;
import org.junit.Before;
import org.junit.Test;
import org.wfg.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.wfg.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.wfg.springframework.core.io.DefaultResourceLoader;

public class ApiTest {
    private DefaultResourceLoader resourceLoader;
    @Before
    public void init(){
        resourceLoader =new DefaultResourceLoader();
    }

    @Test
    public void test(){
        //1 初始化工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //2 读取文件和注册bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

/*        UserService userService = beanFactory.getBean("userService",UserService.class);
        System.out.println(userService.queryUserInfo());*/

        UserDao userDao = beanFactory.getBean("userDao", UserDao.class);

        System.out.println(userDao);


    }
}
