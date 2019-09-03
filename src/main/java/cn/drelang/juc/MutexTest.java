package cn.drelang.juc;

import sun.awt.Mutex;

/**
 * Created by Drelang on 2019/9/1 15:12
 */

public class MutexTest {
    public static void main(String[] args) {
        final Mutex mutex = new Mutex();
        Thread t1 = new Thread(()->{
           try {
               mutex.lock();
               for (;;) {
                   Thread.sleep(1000);
                   System.out.println("this is thread 1");
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               mutex.unlock();
           }
        });

        Thread t2 = new Thread(()->{
           try {
               mutex.lock();
               for (;;) {
                   Thread.sleep(1000);
                   System.out.println("this is thread 2");
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               mutex.unlock();
           }
        });

        t1.start();
        t2.start();
    }
}
