package cn.drelang.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Drelang on 2019/9/27 15:25
 */

public class FizzBuzzMultithreaded {
    public static void main(String[] args) {
        FizzBuzz fz = new FizzBuzz(15);
        ExecutorService pool = Executors.newFixedThreadPool(4);

        pool.execute(()->{
            try {
                fz.fizz(()-> System.out.println("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.execute(()->{
            try {
                fz.buzz(()-> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.execute(()->{
            try {
                fz.fizzbuzz(()-> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.execute(()->{
            try {
                fz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.shutdown();
    }
}

class FizzBuzz {
    private int n;
    private volatile int state = -1;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i=3; i<=n; i+=3) {
            if (i%15 == 0) {    // 15 的倍数不处理，需要跳过，否则运行此方法的线程会一直处于等待状态
                continue;
            }
            lock.lock();
            while (state != 3) {
                condition.await();
            }
            printFizz.run();
            state = -1;
            condition.signalAll();
            lock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i=5; i<=n; i+=5) {
            if (i%15 == 0) {    // 15 的倍数不处理，需要跳过，否则运行此方法的线程会一直处于等待状态
                continue;
            }
            lock.lock();
            while (state != 5) {
                condition.await();
            }
            printBuzz.run();
            state = -1;
            condition.signalAll();
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i=15; i<=n; i+=15) {
            lock.lock();
            while (state != 15) {
                condition.await();
            }
            printFizzBuzz.run();
            state = -1;
            condition.signalAll();
            lock.unlock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number() throws InterruptedException {
        for (int i=1; i<=n; i++) {
            lock.lock();
            while (state != -1) {
                condition.await();
            }
            if (i%3 == 0 && i%5 != 0) {
                state = 3;
            } else if (i%3 != 0 && i%5 == 0) {
                state = 5;
            } else if (i%3 == 0 && i%5 == 0) {
                state = 15;
            } else {
                System.out.println(i);
            }
            condition.signalAll();
            lock.unlock();
        }
    }
}