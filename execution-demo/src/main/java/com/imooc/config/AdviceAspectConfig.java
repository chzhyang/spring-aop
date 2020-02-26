package com.imooc.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.omg.CORBA.Object;
import org.springframework.stereotype.Component;

/**
 * @Before("matchAnno()")
 * @After("matchAnno())") //相当于finally
 * @AfterReturning("matchException()")
 * @AfterThrowing("matchException()")
 * @Around("matchException()")
 * @Before(value = "matchLongArg() && args(productId)")
 * public void beforeWithArgs(Long productId)
 * @AfterReturning(value = "matchReturn()",returning = "returnValue")
 * public void getReulst(Object returnValue)
 * Created by cat on 2017-02-19.
 */
@Aspect //切面类注解
@Component //bean注解
public class AdviceAspectConfig {

    /******pointcut********/

    @Pointcut("@annotation(com.imooc.anno.AdminOnly) && within(com.imooc..*)")
    public void matchAnno(){}

    @Pointcut("execution(* *..find*(Long)) && within(com.imooc..*) ")
    public void matchLongArg(){}

    @Pointcut("execution(public * com.imooc.service..*Service.*(..) throws java.lang.IllegalAccessException) && within(com.imooc..*)")
    public void matchException(){}

    @Pointcut("execution(String com.imooc..*.*(..)) && within(com.imooc..*)")
    public void matchReturn(){}


    /*****advice*******/
//    @AfterReturning(value = "matchReturn()", returning = "result")
//    public void after(java.lang.Object result){
//        System.out.println("###after returning");
//    }
////    @Before("matchLongArg() && args(productId)")//参数校验
////    public void before(Long productId){
////        System.out.println("###before,get args:"+productId);
////    }
    @Around("matchException()")
    public java.lang.Object after(ProceedingJoinPoint joinPoint){
        System.out.println("###before");
        java.lang.Object result = null;
        try{
            result = joinPoint.proceed(joinPoint.getArgs());
            System.out.println("###after returning");
        }catch (Throwable e){
            System.out.println("###ex");
            //throw
            e.printStackTrace();
        }finally {
            System.out.println("###finally");
        }
        return result;
    }

}
