package spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import spring.beans.BeansException;
import spring.beans.PropertyValue;
import spring.beans.PropertyValues;
import spring.beans.factory.DisposableBean;
import spring.beans.factory.InitializingBean;
import spring.beans.factory.config.AutowireCapableBeanFactory;
import spring.beans.factory.config.BeanDefinition;
import spring.beans.factory.config.BeanPostProcessor;
import spring.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractAutowireCapableBeanFactory extends  AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean= null;
        try{
            //创建bean
            bean= createBeanInstance(beanDefinition,beanName,args);
            //给bean填充属性
            applyPropertyValues(beanName,bean,beanDefinition);
            //执行bean的初始化方法
            bean=initializeBean(beanName,bean,beanDefinition);
        }catch (Exception e){
            throw  new BeansException("Instantiation of bean failed", e);
        }
        //注册实现了DisableBean 接口的bean对象
        registerDisposableBeanIfNecessary(beanName,bean,beanDefinition);
        addSingleton(beanName,bean);
        return  bean;
    }

    protected  void registerDisposableBeanIfNecessary(String beanName,Object bean,BeanDefinition beanDefinition){
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean,beanName,beanDefinition));
        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition,String beanName,Object[] args){
        Constructor constructor=null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for(Constructor ctor: declaredConstructors){
            if(null!=args && ctor.getParameterTypes().length==args.length){
                constructor = ctor;
                break;
            }
        }
        return  getInstantiationStrategy().instantiate(beanDefinition,beanName,constructor,args);
    }
     public void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue  pro : propertyValues.getPropertyValue() ){
                String name = pro.getName();
                Object value = pro.getValue();
                if(value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value=getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean,name,value);
            }
        }catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
     }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    private Object initializeBean(String beanName,Object bean,BeanDefinition beanDefinition){
        // 执行 beanPostProcessor
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);
        // 执行 bean对象的初始化方法
        try{
            invokeInitMethods(beanName,wrappedBean,beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }
        // 执行 beanPostProcessor after
        wrappedBean =applyBeanPostProcessorsAfterInitialization(bean,beanName);
        return  wrappedBean;
    }

    private void invokeInitMethods(String beanName,Object bean,BeanDefinition beanDefinition) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 实现接口 initializingBean
        if(bean instanceof InitializingBean){
             ((InitializingBean) bean).afterPropertiesSet();
        }

        //注解配置 init-method
        String initMethodName = beanDefinition.getInitMethodName();
        if(StrUtil.isNotEmpty(initMethodName)){
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if(null==initMethod){
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }


    }

 public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean,String  beanName){
        Object result = existingBean;
        for(BeanPostProcessor processor: getBeanPostProcessorList()){
            Object current = processor.postProcessBeforeInitialization(result,beanName);

            if(null == current){
                 return  result;
            }

            result = current;

        }
     return  result;
 }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        Object result =existingBean;
        for(BeanPostProcessor processor:getBeanPostProcessorList()){
            Object current = processor.postProcessAfterInitialization(result,beanName);
            if(null==current){
                return  result;
            }
            result = current;

        }
        return  result;
    }
}
