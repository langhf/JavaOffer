package cn.drelang.juc.lockSupport;

import java.util.concurrent.TimeUnit;

/**
 * monitor 锁使用时没有放在同步块中，必须用 synchronized，
 *  因此会报错 java.lang.IllegalMonitorStateException
 * Created by Drelang on 2019/9/30 22:35
 */

public class MonitorTestWrong {
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()-> {
            String name = Thread.currentThread().getName();
            System.out.println(System.currentTimeMillis() + "," + name + " start");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "," + name + " 运行完毕");
        });

        thread.setName("t1");
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        lock.notify();
    }
}
