package cn.drelang.designPattern;

import java.util.Arrays;

/**
 * Created by Drelang on 2020/05/17 17:33
 */

public class Factory {
    public static void main(String[] args) {
        IMessage message = MyFactory.getInstance("cn.drelang.designPattern.MessageImpl");
        message.send("hello");
    }
}

class MyFactory {
    public static <T> T getInstance(String className ) {
        T res = null;
        try {
            res = (T)Class.forName(className).getDeclaredConstructor().newInstance();
//            System.out.println(res.getClass().getDeclaredConstructor());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}

interface IMessage {
    void send(String msg);
}

class MessageImpl implements IMessage {
    public void send(String msg) {
        System.out.println("发送消息：" + msg);
    }
}

class MessageImpl1 implements IMessage {
    public void send(String msg) {
        System.out.println("发送消息：" + msg);
    }
}

interface IService {
    void service();
}

class HouseService implements IService {
    public void service() {
        System.out.println("住房服务");
    }
}