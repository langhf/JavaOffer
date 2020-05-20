package cn.drelang.java.advanced.threads;

/**
 * 多线程的一些基本操作。
 * 多线程的设计之中，使用了代理设计模式的结构，用户自定义的线程主体只是负责项目核心功能的实现，
 *      所有的辅助实现全部交由Thread类来处理。
 * 主线程负责处理整体流程，而子线程负责处理耗时操作。
 * Created by Drelang on 2020/05/19 14:26
 */

public class BaseOperation {
    public static void main(String[] args) {
        MyThread thread = new MyThread("线程1");
        thread.start();

        Thread thread1 = new Thread(new MyThread1("线程2"));
        thread1.start();

        for (int x=0; x<3; x++) {
            String title = "线程对象-" + x;
            Runnable run = ()->{
                for (int y=0; y<100; y++) {
                    System.out.println(title + "运行，y=" + y);
                }
            };
            new Thread(run).start();
        }
    }
}

class MyThread extends Thread {
    private String title;

    public MyThread(String title) {
        this.title = title;
    }

    public void run() {
        for (int i=0; i<100; i++) {
            System.out.println("i=" + i);
        }
    }
}

class MyThread1 implements Runnable {
    private String title;

    public MyThread1(String title) {
        this.title = title;
    }

    public void run() {
        for (int i=0; i<100; i++) {
            System.out.println("i=" + i);
        }
    }
}
