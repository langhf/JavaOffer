package cn.drelang.juc.lockSupport;

import java.util.concurrent.TimeUnit;

/**
 * monitor 锁需要放在同步块中使用，必须用 synchronized
 * Created by Drelang on 2019/9/30 21:31
 */

public class MonitorTest {
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()-> {
            synchronized(lock) {
                String name = Thread.currentThread().getName();
                System.out.println(System.currentTimeMillis() + "," + name + " start");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "," + name + " 运行完毕");
            }
        });

        thread.setName("t1");
        thread.start();
        TimeUnit.SECONDS.sleep(3);
        synchronized (lock) {
            lock.notify();
        }
    }
}
