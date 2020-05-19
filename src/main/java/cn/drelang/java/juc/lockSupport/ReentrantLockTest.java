package cn.drelang.java.juc.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 使用时需要在 lock 块内
 *
 * Created by Drelang on 2019/10/8 09:44
 */

public class ReentrantLockTest {
    static private ReentrantLock lock = new ReentrantLock();
    static private Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            lock.lock();
            try {
                System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " Start");
                condition.await();
                System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t.setName("t1");
        t.start();
        TimeUnit.SECONDS.sleep(5);
        lock.lock();
        try {
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}
