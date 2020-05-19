package cn.drelang.designPattern;

/**
 * Created by Drelang on 2020/05/17 17:48
 */

public class Singleton {
    public static void main(String[] args) {
        for (int x = 0; x < 5; x++) {
            new Thread(()-> {
                MySingleton.getInstance().print();
            }, "单例消费端-"+x).start();
        }
    }
}

class MySingleton {
    private static volatile MySingleton instance = null;

    private MySingleton() {
        System.out.println(Thread.currentThread().getName() + "******实例化MySingleton类对象******");
    }

    public static MySingleton getInstance() {
        if (instance == null) {
            synchronized (MySingleton.class) {
                if (instance == null) {
                    instance = new MySingleton();
                }
            }
        }
        return instance;
    }

    public void print() {
        System.out.println("hello");
    }
}

