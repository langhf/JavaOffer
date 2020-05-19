package cn.drelang.java.advanced.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/**
 * 使用 Annotation 之后最大的特点是可以结合反射机制实现程序的处理
 * Created by Drelang on 2020/05/17 16:07
 */

public class Annotation {
    public static void main(String[] args) throws Exception {
        Method method = Message.class.getMethod("send");
        DefaultAnnotation anno = method.getAnnotation(DefaultAnnotation.class);
        System.out.println(anno.title());
        System.out.println(anno.url());
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface DefaultAnnotation {
    public String title();  // 获取数据
    public String url() default "www.drelang.cn";   // 获取数据，默认值
}

class Message {
    @DefaultAnnotation(title = "drelang")
    public void send() {
        System.out.println("消息发送");
    }
}