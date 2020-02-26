package com.imooc.pattern;

/**
 * Created by cat on 2017-02-27.
 */
//静态代理，编译前写死，不利于扩展
public class Proxy implements Subject{
    //代理类要强引用，注入被代理的真实对象
    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        System.out.println("before");
        try{
            realSubject.request();
        }catch (Exception e){
            System.out.println("ex:"+e.getMessage());
            throw e;
        }finally {
            System.out.println("after");
        }
    }

    @Override
    public void hello() {
        realSubject.hello();
    }
}
