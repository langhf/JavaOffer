package cn.drelang.java.juc.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by Drelang on 2019/10/8 10:14
 */

public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " start");
            LockSupport.park();
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + " 被唤醒");
        });
        t.setName("t1");
        t.start();
        TimeUnit.SECONDS.sleep(5);
        LockSupport.unpark(t);
        System.out.println(System.currentTimeMillis() + ", LockSupport.unpart() 执行完毕");
    }
}
