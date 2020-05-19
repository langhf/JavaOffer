package cn.drelang.designPattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Drelang on 2020/05/17 15:25
 */

public class Cglib {
    public static void main(String[] args) {
        Message realObject = new Message();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(realObject.getClass());
        enhancer.setCallback(new CgProxy(realObject));
        Message proxyObject = (Message) enhancer.create();
        proxyObject.send();
    }
}

class CgProxy implements MethodInterceptor {
    Object target;

    public CgProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object res = null;
        if (this.connect()) {
            res = method.invoke(this.target, args);
            this.close();
        }
        return res;
    }

    public boolean connect() {
        System.out.println("进行消息通道的连接");
        return true;
    }

    public void close() {
        System.out.println("关闭消息通道");
    }
}

class Message {
    public void send() {
        System.out.println("发送消息");
    }
}

