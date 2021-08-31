package wfg.springframework;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import wfg.springframework.bean.User;

/**
 *  spring 中不仅仅是注册信息，还有管理bean的生命周器(后几章学习)
 */
public class BeanDefinitionTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test_BeanFactory()
    {
        //初始化beanFactory
        BeanFactory beanFactory = new BeanFactory();
        // beanDefinition获取bean定义的信息，并放入到spring容器中
        BeanDefinition beanDefinition = new BeanDefinition(new User());
        beanFactory.setBeanDefinition("user",beanDefinition);
        //通过beanFactory即可获取bean对象
        User user= (User)beanFactory.getBean("user");
        user.work();
    }
}
