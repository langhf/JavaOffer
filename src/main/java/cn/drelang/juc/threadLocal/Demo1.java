package cn.drelang.juc.threadLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 使用 ThreadPoolExecutor 执行任务，模拟 controller, service, dao 操作。
 * 使用 ThreadLocal 将事务 ID 或 用户 ID 与特定线程绑定。
 *
 * Created by Drelang on 2019/08/14 15:03
 */

public class Demo1 {

    static ThreadLocal<String> traceIdKd = new ThreadLocal<>();

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
        System.out.println("****" + System.currentTimeMillis() + "[traceId:" + traceId + "]," + ",[线程：" + Thread.currentThread().getName() + "]," + stack[1] + ":" + msg);
    }

    static void controller(List<String> dataList) {
        log("接收请求");
        service(dataList);
    }

    static void service(List<String> dataList) {
        log("处理请求");
        dao(dataList);
    }

    static void dao(List<String> dataList) {
        log("执行数据库操作");
        for (String s : dataList) {
            log("插入数据" + s + "成功");
        }
    }

    public static void main(String[] args) {
        // 创建要处理的数据
        List<String> dataList = new ArrayList<>();
        for (int i=0; i<3; i++) {
            dataList.add(String.valueOf(i));
        }
        // 模拟 5 个请求
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