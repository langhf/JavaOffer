package cn.drelang.java.juc.threadLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用 ThreadPoolExecutor 执行任务，模拟 controller, service, dao 操作。
 * 参考链接：https://mp.weixin.qq.com/s/pVqfZ-vHUf9kPUpRlfa1Qw
 *
 * Created by Drelang on 2019/08/14 14:23
 */

public class Demo {

    // 标记线程号
    static AtomicInteger threadIndex = new AtomicInteger(0);

    // 创建处理请求的线程池
    static ThreadPoolExecutor disposeRequestExecutor = new ThreadPoolExecutor(3,
            3,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(),
            r -> {
                Thread thread = new Thread(r);
                thread.setName("disposeRequestThread-" + threadIndex.getAndIncrement());
                return thread;
            });

    static void log(String msg) {
        StackTraceElement[] stack = (new Throwable()).getStackTrace();
        System.out.println("****" + System.currentTimeMillis() + ",[线程：" + Thread.currentThread().getName() + "]," + stack[1] + "：" + msg);
    }

    static void controller(List<String> dataList) {
        log("接受请求");
        service(dataList);
    }

    static void service(List<String> dataList) {
        log("执行业务");
        dao(dataList);
    }

    static void dao(List<String> dataList) {
        log("执行数据库操作");
        for (String s : dataList) {
            log("插入数据 " + s + "成功");
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
            disposeRequestExecutor.execute(() -> {
                controller(dataList);
            });
        }
        disposeRequestExecutor.shutdown();
    }
}