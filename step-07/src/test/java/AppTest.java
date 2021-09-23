import org.wfg.UserService;
import org.junit.Test;
import spring.context.support.ClassPathXmlApplicationContext;

public class AppTest {
    @Test
    public void test01(){
        // 初始化
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        //获取bean
        UserService userService = applicationContext.getBean("userService",UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

}
