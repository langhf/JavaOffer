package cn.drelang.algorithm.stack;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TwoStackForQueueTest {
    private ThreadLocal<String> traceIdKd = new ThreadLocal<>();
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(3,
            3,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            (ThreadFactory) Thread::new);

    private TwoStackForQueue queue = new TwoStackForQueue();

    private void log(String msg) {
        String id = traceIdKd.get();
        System.out.println(id + ": " + msg);
    }

    @Test
    public void testMethod1() {
        TwoStackForQueue.Method1<Integer> q = queue.new Method1<>();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                String id = traceIdKd.get();
                q.offer(1);
                q.offer(2);
                q.offer(3);
                System.out.println(q.poll());
                System.out.println(q.poll());
                q.offer(4);
                System.out.println(q.poll());
                System.out.println(id+q.poll());
            }
        };

        for (int i=0; i<3; i++) {
            String traceId = "Thread-" + i;
            executor.execute(() -> {
                try {
                    traceIdKd.set(traceId);
                    q.offer(1);
                    q.offer(2);
                    q.offer(3);
                    System.out.println(q.poll());
//                    log(q.poll().toString());
                    System.out.println(q.poll());
                    q.offer(4);
                    System.out.println(q.poll());
                    System.out.println(q.poll());
                } finally {
                    traceIdKd.remove();
                }
            });
        }
    }

    @Test
    public void testMethod2() {
        TwoStackForQueue.Method2<Integer> q = queue.new Method2<>();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                q.offer(1);
                q.offer(2);
                q.offer(3);
                System.out.println(q.poll());
                System.out.println(q.poll());
                System.out.println(q.poll());
            }
        };
        new Thread(r).start();
    }
}