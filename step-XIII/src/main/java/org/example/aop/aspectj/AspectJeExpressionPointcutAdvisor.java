package org.example.aop.aspectj;

import org.aopalliance.aop.Advice;
import org.example.aop.Pointcut;
import org.example.aop.PointcutAdvisor;
import org.example.aop.aspectj.AspectJExpressionPointcut;

/**
 * 切面的实现
 */
public class AspectJeExpressionPointcutAdvisor implements PointcutAdvisor {

    //切点 表达式的
    private AspectJExpressionPointcut pointcut;

    // 具体的拦截方法
    private Advice advice;

    // 表达式
    private String expression;


    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if(null == pointcut){
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
