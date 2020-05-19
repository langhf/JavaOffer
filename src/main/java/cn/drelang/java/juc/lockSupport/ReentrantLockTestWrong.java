package cn.drelang.java.juc.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 没有放在 lock 里面的话会出现错误
 *
 * Created by Drelang on 2019/10/8 09:57
 */

public class ReentrantLockTestWrong {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            String name = Thread.currentThread().getName();
            System.out.println(System.currentTimeMillis() + "," + name + " start");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "," + name + " finish");

        });
        t.setName("t1");
        t.start();
        TimeUnit.SECONDS.sleep(5);

        condition.signalAll();
    }
}
