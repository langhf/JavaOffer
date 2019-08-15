package cn.drelang.juc.deadLock;

import java.util.concurrent.*;

/**
 * 饥饿（Starvation）死锁的一个例子。
 *
 * Created by Drelang on 2019/08/15 21:25
 */

public class ExecutorLock {
    private static ExecutorService single = Executors.newSingleThreadExecutor();

    public static class AnotherCallable implements Callable<String> {
        public String call() throws Exception {
            System.out.println("in AnotherCallable");
            return "another success";
        }
    }

    public static class MyCallable implements Callable<String> {
        public String call() throws Exception {
            System.out.println("in MyCallable");
            Future<String> f = single.submit(new AnotherCallable());
            // 永远 get 不到结果，因为执行器内只有一个线程，已经被 MyCallable 占用了！
            return "success:" + f.get();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable task = new MyCallable();
        Future<String> f =  single.submit(task);
        System.out.println(f.get());
        System.out.println("over");
        single.shutdown();
    }
}