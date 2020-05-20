package cn.drelang.java.advanced.threads;

import java.util.TreeMap;

/**
 * Created by Drelang on 2020/05/19 20:35
 */

public class Sleep {
    public static void main(String[] args) throws Exception {
//        m1();
//        m2();
//        m3();
//        m4();
        m5();
    }

    // 线程基础用法
    public static void m1() {
        for (int num=0; num<5; num++) {
            new Thread(()->{
                for (int x=0; x<10; x++) {
                    System.out.println(Thread.currentThread().getName()+"：x = " + x);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "线程对象-" + num).start();
        }
    }

    // 中断正在执行的线程
    public static void m2() throws Exception{
        Thread thread = new Thread(()->{
            System.out.println("*** 开始睡眠 ***");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("*** 结束睡眠 ***");
        });
        thread.start();
        Thread.sleep(1000);
        if (!thread.isInterrupted()) {
            thread.interrupt(); // 中断执行
        }
    }

    // 线程强制执行
    public static void m3() throws Exception {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(()->{
            for (int x=0; x<100; x++) {
                if (x == 3) {
                    try {
                        // 等待主线程结束再运行子线程
                        mainThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"执行 x=" + x);
            }
        }, "玩耍的线程");
        thread.start();
        for (int x=0; x<100; x++) {
            Thread.sleep(100);
            System.out.println("【霸道的main线程】number = " + x);
        }
    }

    // 线程礼让
    public static void m4() throws Exception {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(()->{
            for (int x=0; x<100; x++) {
                if (x % 3 == 0) {
                    // 调用 yield() 方法只会礼让一次
                    Thread.yield();
                    System.out.println("### 玩耍的线程礼让 ###");
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"执行 x=" + x);
            }
        }, "玩耍的线程");
        thread.start();
        for (int x=0; x<100; x++) {
            Thread.sleep(100);
            System.out.println("【霸道的main线程】number = " + x);
        }
    }

    // 线程优先级, 优先级大的有可能先执行，但不是绝对的
    // 主线程属于中等优先级，默认创建的线程也是中等优先级
    public static void m5() throws Exception {
        Thread thread1 = new Thread(()->{
            for (int x=0; x<100; x++) {
                System.out.println("线程A执行, 优先级-" + Thread.currentThread().getPriority());
            }
        });
        Thread thread2 = new Thread(()->{
            for (int x=0; x<100; x++) {
                System.out.println("线程B执行，优先级-" + Thread.currentThread().getPriority());
            }
        });
        Thread thread3 = new Thread(()->{
            for (int x=0; x<100; x++) {
                System.out.println("线程C执行，优先级-" + Thread.currentThread().getPriority());
            }
        });

        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread3.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

