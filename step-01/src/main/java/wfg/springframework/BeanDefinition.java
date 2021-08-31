package wfg.springframework;

/**
 * spring 包含两部分，一是将对象信息加载到容器中，二是将bean的实例化。 定义 ---注册到spring容器中--获取
 *  今天来学习BeanDefinition ,此作用就是  保存定义的bean实力化信息
 *    BeanFactory 表示bean对象的工厂，用于获取bean
 */
public class BeanDefinition
{
    //所保存定义的bean信息
    private Object bean;

    public Object getBean() {
        return bean;
    }

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }
}
