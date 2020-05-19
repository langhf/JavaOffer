package cn.drelang.java.advanced.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Drelang on 2020/05/17 16:23
 */

public class FactoryAnnotation {
    public static void main(String[] args) {
        MessageService messageService = new MessageService();
        messageService.send("开始");
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface UseMessage {
    public Class<?> clazz();
}

@UseMessage(clazz = MessageImpl.class)
class MessageService {
    private IMessage message;

    public MessageService() {
//        this.message = Factory.getInstance(MessageImpl.class);
        UseMessage use = this.getClass().getAnnotation(UseMessage.class);
        this.message = (IMessage) Factory.getInstance(use.clazz());
    }

    public void send(String msg) {
        this.message.send(msg);
    }
}

class Factory {
    private Factory() {}

    public static <T> T getInstance(Class<T> clazz) {
        try {
            return (T) new MessageProxy().bind(clazz.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

class MessageProxy implements InvocationHandler {
    private Object target;

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        if (this.connect()) {
            obj = method.invoke(target, args);
            this.close();
        }
        return obj;
    }

    public boolean connect() {
        System.out.println("【代理操作】连接消息发送通道");
        return true;
    }

    public void close() {
        System.out.println("【代理操作】关闭消息通道");
    }
}

interface IMessage {
    void send(String msg);
}

class MessageImpl implements IMessage {
    public void send(String msg) {
        System.out.println("发送消息:" + msg);
    }
}
