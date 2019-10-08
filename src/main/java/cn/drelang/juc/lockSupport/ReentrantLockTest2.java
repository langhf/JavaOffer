package cn.drelang.juc.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在 await() 之前唤醒线程，是不起作用的。
 *
 * Created by Drelang on 2019/10/8 10:04
 */

public class ReentrantLockTest2 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            lock.lock();
            try {
                String name = Thread.currentThread().getName();
                try {
                    // 休眠 5 秒
                    TimeUnit.SECONDS.sleep(5);
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + "," + name + " start");
            } finally {
                lock.unlock();
            }
        });
        t.setName("t1");
        t.start();
        // 比 t1 线程休眠时间要短
        TimeUnit.SECONDS.sleep(3);
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
        System.out.println(System.currentTimeMillis() + ",condition.signal() 执行完毕");
    }
}
