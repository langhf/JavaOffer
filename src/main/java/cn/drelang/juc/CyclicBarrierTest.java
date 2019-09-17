package cn.drelang.juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * CyclicBarrier 使用示例.
 * 此例中，CyclicBarrier 可以循环使用，而且每一次所有线程到达汇合处的时候，
 *  最后到达的线程执行了 barrierAction 的 Runnable 对象。
 *
 * 参考：https://www.cnblogs.com/nullzx/p/5271964.html
 *
 * Created by Drelang on 2019/9/17 10:03
 */

public class CyclicBarrierTest {

    private final CyclicBarrier barrier;
    private final Random random = new Random();

    CyclicBarrierTest(int parties, Runnable barrierAction) {
        barrier = new CyclicBarrier(parties, barrierAction);
    }

    class Task implements Runnable {

        private final int id;

        Task(int id) {
            this.id = id;
        }

        public void run() {
            try {
                String name = Thread.currentThread().getName();
                int time = 0;
                while(true) {
                    Thread.sleep(random.nextInt(2000));
                    System.out.println(name + ": start" + id + "-time: " + time);
                    barrier.await();
                    System.out.println(name + ": finished-time：" + time++);
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;
        // barrierAction 是由最后到达屏障点的线程执行！
        Runnable barrierAction = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": All tasks arrived!");
            }
        };
        CyclicBarrierTest test = new CyclicBarrierTest(n, barrierAction);

//        for (int i=0; i<n; i++) {
//            new Thread(test.new Task(i)).start();
//        }

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                4,
                4,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );

        for (int i=0; i<n; i++) {
            executor.execute(test.new Task(i));
        }
        executor.shutdown();
    }
}
