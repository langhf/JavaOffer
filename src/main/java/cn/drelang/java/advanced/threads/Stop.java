package cn.drelang.java.advanced.threads;

/**
 * 线程柔和停止
 * Thread 类下面的 stop, suspend, resume 方法明确废弃使用！
 * Created by Drelang on 2020/05/20 14:43
 */

public class Stop {
    private static boolean flag;

    public static void main(String[] args) throws Exception{
        flag = true;
        new Thread(()->{
            long num=0;
            while (flag) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "num = " + num++);
            }
        }, "执行线程").start();
        Thread.sleep(200);
        flag = false;   // 停止线程
    }
}

