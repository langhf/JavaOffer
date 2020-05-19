package cn.drelang.java.juc.deadLock;

/**
 * 最常见的一种死锁
 *
 * Created by Drelang on 2019/08/15 18:18
 */

public class DeadLock {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        Thread t1 = new Thread(() -> {
            String thradName = Thread.currentThread().getName();
            synchronized(o1) {
                try {
                    System.out.println(thradName + " Now, I get o1.");
                    Thread.sleep(1000);
                    synchronized (o2) {
                        System.out.println(thradName + " Now, I get o2.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        Thread t2 = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            synchronized(o2) {
                try {
                    System.out.println(threadName + " Now, I get o2.");
                    Thread.sleep(1000);
                    synchronized (o1) {
                        System.out.println(threadName + " Now, I get o1.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}

