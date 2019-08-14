package cn.drelang.designPattern.singleton;

/**
 * Created by Drelang on 2019/08/13 19:56
 */

public class All {
}

/**
 * 饿汉式
 */
// 1. 枚举方式
enum Singleton {
    INSTANCE;
    public void doSomething(){}
}

// 2. 线程安全
class Singleton1 {
    private final static Singleton1 instance = new Singleton1();

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return instance;
    }
}

/**
 * 懒汉式
 */
// 1. 双重检查加锁
class Singleton2 {
    private volatile static Singleton2 instance;

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        if (instance == null) {
            synchronized(Singleton2.class) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}

// 2. 线程安全
class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {

    }

    public static synchronized Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}

// 3. 静态内部类方式
class Singleton4 {
    private static class SingletonHolder {
        private static final Singleton4 INSTANCE = new Singleton4();
    }

    private Singleton4() {

    }

    public static Singleton4 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
