package com.imooc.dynamic;
//引入被代理的类
import com.imooc.pattern.RealSubject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * aspect
 * Created by cat on 2017-02-27.
 */
//实现了handler接口的代理调用器，有了它才动态生成代理类
public class JdkProxySubject implements InvocationHandler{
    //强引用，注入要代理的类
    private RealSubject realSubject;

    public JdkProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    //invoke解决了静态代理的缺点，不需要手动增加被代理方法，通过反射调用注入的真实类的方法
    //调用哪个方法，任由client访问者选择
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //在invoke中实现了切入点和通知
        System.out.println("before");
        Object result = null;
        try{
            result = method.invoke(realSubject,args);
        }catch (Exception e){
            System.out.println("ex:"+e.getMessage());
            throw e;
        }finally {
            System.out.println("after");
        }
        return result;
    }
}
