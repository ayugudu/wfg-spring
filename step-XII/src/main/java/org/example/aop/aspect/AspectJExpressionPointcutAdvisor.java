package org.example.aop.aspect;

import org.aopalliance.aop.Advice;
import org.aspectj.weaver.tools.PointcutParser;
import org.example.aop.Pointcut;
import org.example.aop.PointcutAdvisor;

/**
 * 切面的实现
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    //advice
    private Advice advice;

    //表达式
    private String expression;



    public void setExpression(String expression){
        this.expression =expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
