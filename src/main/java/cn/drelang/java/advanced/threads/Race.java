package cn.drelang.java.advanced.threads;

/**
 * 利用一个 Runnable 实例，创建多个线程。
 * Created by Drelang on 2020/05/19 15:22
 */

public class Race {
    public static void main(String[] args) {
        SellTicket sellTicket = new SellTicket();
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();
    }
}

class SellTicket implements Runnable {
    private volatile int ticket = 5;
    public void run() {
        for (int i=0; i<10; i++) {
            if (ticket > 0) {
                System.out.println("ticket=" + this.ticket--);
            }
        }
    }
}