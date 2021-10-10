package org.example.aop.framework.autoproxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.example.aop.*;
import org.example.aop.aspect.AspectJExpressionPointcutAdvisor;
import org.example.aop.framework.ProxyFactory;
import org.example.beans.factory.BeanFactory;
import org.example.beans.factory.BeanFactoryAware;
import org.example.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.example.beans.factory.support.DefaultListableBeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * 自动代理的实现
 * 会自动搜寻容器内所有的advisor 信息（切面），然后根据其拦截信息，为其生成相应的代理对象
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
         this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object PostProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

    /**
     * 自动装配逻辑
     * @param beanClass
     * @param beanName
     * @return
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
       if(isInfrastructureClass(beanClass)){
           return  null;
       }
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
         for(AspectJExpressionPointcutAdvisor advisor:advisors){
             ClassFilter classFilter =advisor.getPointcut().getClassFilter();

             if(!classFilter.matches(beanClass)){
                 continue;
             }

             AdvisedSupport advisedSupport = new AdvisedSupport();

             TargetSource targetSource = null;

             try {
                 targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
             } catch (Exception e) {
                 e.printStackTrace();
             }
             advisedSupport.setTargetSource(targetSource);
             advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
             advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
             advisedSupport.setProxyTargetClass(false);
             return  new ProxyFactory(advisedSupport).getProxy();
         }


        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass){

     return  Advice.class.isAssignableFrom(beanClass)|| Pointcut.class.isAssignableFrom(beanClass)|| Advisor.class.isAssignableFrom(beanClass);
    }


}
