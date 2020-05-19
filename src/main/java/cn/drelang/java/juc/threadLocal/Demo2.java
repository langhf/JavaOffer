package cn.drelang.java.juc.threadLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  InheritableThreadLocal 可以使得子线程复制父线程的线程内变量。
 *
 * Created by Drelang on 2019/08/14 15:49
 */

public class Demo2 {

    static InheritableThreadLocal<String> traceIdKd = new InheritableThreadLocal<>();

    static AtomicInteger threadId = new AtomicInteger(0);

    static ThreadPoolExecutor disposeRequestExecutor = new ThreadPoolExecutor(3,
            3,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            r -> {
                Thread t = new Thread(r);
                t.setName("disposeRequestThread-" + threadId.getAndIncrement());
                return t;
            });

    static void log(String msg) {
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        String traceId = traceIdKd.get();
        System.out.println("****" + System.currentTimeMillis() + "[traceId:" + traceId + "]," + "[线程：" + Thread.currentThread().getName() + "]," + stack[1] + msg);
    }

    static void controller(List<String> dataList) {
        log("接受请求");
        service(dataList);
    }

    static void service(List<String> dataList) {
        log("处理请求");
        dao(dataList);
    }

    static void dao(List<String> dataList) {
        CountDownLatch countDownLatch = new CountDownLatch(dataList.size());

        log("执行数据库操作");
        String threadName = Thread.currentThread().getName();
        // 模拟插入数据
        for (String s : dataList) {
            new Thread(() -> {
                try {
                    // 模拟数据库操作耗时 100 毫秒
                    TimeUnit.MICROSECONDS.sleep(100);
                    log("插入数据" + s + "成功，主线程" + threadName);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        // 等待上面的 dataList 处理完毕
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //需要插入的数据
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            dataList.add("数据" + i);
        }

        for (int i=0; i<5; i++) {
            String traceId = String.valueOf(i);
            disposeRequestExecutor.execute(() -> {
                traceIdKd.set(traceId);
                try {
                    controller(dataList);
                } finally {
                    traceIdKd.remove();
                }
            });

        }

        disposeRequestExecutor.shutdown();
    }
}

