package cn.drelang.java.advanced.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 竞赛抢答Demo：三个人抢答，每次只准一个人成功抢答，抢答成功给予提示
 * Created by Drelang on 2020/05/20 15:18
 */

public class ContestAnswer {
    public static void main(String[] args) throws Exception {
        Contest contest = new Contest();
        FutureTask<String> taskA = new FutureTask<>(contest);
        FutureTask<String> taskB = new FutureTask<>(contest);
        FutureTask<String> taskC = new FutureTask<>(contest);
        new Thread(taskA, "竞赛者A").start();
        new Thread(taskB, "竞赛者B").start();
        new Thread(taskC, "竞赛者C").start();
        System.out.println(taskA.get());
        System.out.println(taskB.get());
        System.out.println(taskC.get());
    }

}

class Contest implements Callable<String> {
    private boolean flag = false;

    public String call() {
        synchronized (this) {
            if (!flag) {
                flag = true;
                return Thread.currentThread().getName() + "抢答成功！";
            } else {
                return Thread.currentThread().getName() + "抢答失败！";
            }
        }
    }
}