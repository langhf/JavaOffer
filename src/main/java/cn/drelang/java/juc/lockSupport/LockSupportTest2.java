package cn.drelang.java.juc.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 即使 unpark() 方法在 park() 方法之前调用，也是可以唤醒线程的
 *
 * Created by Drelang on 2019/10/8 10:18
 */

public class LockSupportTest2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.park();
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 被唤醒");
        });
        t.setName("t1");
        t.start();

        TimeUnit.SECONDS.sleep(3);
        LockSupport.unpark(t);
        System.out.println(System.currentTimeMillis() + ",LockSupport.unpark() 执行完毕");
    }
}
