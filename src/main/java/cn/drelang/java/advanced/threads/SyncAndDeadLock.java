package cn.drelang.java.advanced.threads;

/**
 * Created by Drelang on 2020/05/20 10:52
 */

public class SyncAndDeadLock {
    public static void main(String[] args) {
        SaleTicket mt = new SaleTicket();
        new Thread(mt, "票贩子A").start();
        new Thread(mt, "票贩子B").start();
        new Thread(mt, "票贩子C").start();
    }
}

class SaleTicket implements Runnable {
    private int ticket = 10;

    public void run() {
        while (sale());
    }

    private synchronized boolean sale() {
        if (this.ticket > 0) {
            try {
                // 模拟网络延迟
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"卖票，ticket=" + this.ticket--);
            return true;
        } else {
            System.out.println("*******票已经卖光******");
            return false;
        }
    }
}