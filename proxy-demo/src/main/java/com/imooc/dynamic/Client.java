package com.imooc.dynamic;

import com.imooc.pattern.RealSubject;
import com.imooc.pattern.Subject;

import java.lang.reflect.Proxy;

/**
 * System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
 * Created by cat on 2017-02-27.
 */
public class Client {

    public static void main(String[] args){
        //获取生成的字节码放入 $Proxy0.class
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        //返回代理类实例
        //参数3，代理调用处理器，核心
        Subject subject = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(),
                new Class[]{Subject.class},new JdkProxySubject(new RealSubject()));
        //想用哪个用哪个，代理类里不用增加相应的方法
        subject.hello();
        subject.request();
    }
}
