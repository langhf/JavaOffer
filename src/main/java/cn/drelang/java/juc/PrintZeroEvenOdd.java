package cn.drelang.java.juc;

import java.util.concurrent.CountDownLatch;

/**
 * leetcode-1116 打印零与奇偶数
 *
 * Created by Drelang on 2019/9/4 18:45
 */

public class PrintZeroEvenOdd {
    public static void main(String[] args) throws InterruptedException{
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);

        new Thread(()-> {
            try {
                zeroEvenOdd.zero();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()-> {
            try {
                zeroEvenOdd.odd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                zeroEvenOdd.even();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

class ZeroEvenOdd {
    private int n;

    private CountDownLatch latchZero = new CountDownLatch(0);
    private CountDownLatch latchOdd = new CountDownLatch(1);
    private CountDownLatch latchEven = new CountDownLatch(1);

    ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    void zero() throws InterruptedException {
        for (int i=0; i<n; i++) {
            latchZero.await();
            System.out.print(0);
            latchZero = new CountDownLatch(1);
            if (i % 2 == 0) {
                latchOdd.countDown();
            } else {
                latchEven.countDown();
            }
        }
    }

    void even() throws InterruptedException {
        for (int i=1; i<=n; i+=2) {
            latchOdd.await();
            System.out.print(i);
            latchOdd = new CountDownLatch(1);
            latchZero.countDown();
        }
    }

    void odd() throws InterruptedException {
        for (int i=2; i<=n; i+=2) {
            latchEven.await();
            System.out.print(i);
            latchEven = new CountDownLatch(1);
            latchZero.countDown();
        }
    }
}