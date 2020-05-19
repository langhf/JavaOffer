package cn.drelang.java.advanced.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Drelang on 2020/05/18 11:40
 */

public class Demo {
    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafeObj = (Unsafe) field.get(null);    // static 属性不需要传递实例化对象
        // 利用Unsafe类绕过JVM管理机制，可以在没有实例化对象的情况下获取一个Singleton类的对象
        Singleton singleton = (Singleton) unsafeObj.allocateInstance(Singleton.class);
        singleton.print();
    }
}

class Singleton {
    private Singleton() {
        System.out.println("Singleton 构造方法");
    }

    public void print() {
        System.out.println("hello");
    }
}

