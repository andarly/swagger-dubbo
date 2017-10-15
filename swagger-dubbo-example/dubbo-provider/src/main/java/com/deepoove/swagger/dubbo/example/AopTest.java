package com.deepoove.swagger.dubbo.example;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 通过aop，使生成代理对象
 */
@Component
@Aspect
public class AopTest {

    public AopTest(){
        System.out.println("");
    }
    
    @Pointcut("execution(* com.deepoove.swagger.dubbo.example.provider.UserServiceImpl.*(..))")
    public void sleeppoint(){}
    
    @Before("sleeppoint()")
    public void beforeSleep(){
        System.out.println("睡觉前要脱衣服!");
    }
    
    @AfterReturning("sleeppoint()")
    public void afterSleep(){
        System.out.println("睡醒了要穿衣服！");
    }
    
}