package cn.drelang.java.advanced.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by Drelang on 2020/05/19 15:38
 */

public class CallableCase {
    public static void main(String[] args) throws Exception {
        FutureTask<String> task = new FutureTask<>(new CallableThread());
        new Thread(task).start();
        System.out.println("【线程返回数据】：" + task.get());
    }
}

class CallableThread implements Callable<String> {
    public String call() throws Exception {
        for (int x=0; x<10; x++) {
            System.out.println("*******线程执行、x = " + x);
        }
        return "线程执行完毕";
    }
}

