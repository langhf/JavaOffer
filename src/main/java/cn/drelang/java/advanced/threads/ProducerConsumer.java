package cn.drelang.java.advanced.threads;

import java.lang.reflect.Proxy;

/**
 * 基本生产者-消费者模型。
 * 使用 Object 的 wait() 和 notify() 方法实现生产数据与消费数据的同步。
 * 需要注意! object 的 wait() 和 notify() 方法只能被对象的监视器调用，也就是要先同步(synchronized)再调用。
 *
 * Created by Drelang on 2020/05/20 11:54
 */

public class ProducerConsumer {
    public static void main(String[] args) throws Exception {
        Message msg = new Message(true);
        new Thread(new Producer(msg),"【生产者线程】：").start();
        new Thread(new Consumer(msg),"【消费者线程】：").start();
    }
}

class Message {
    private String title;
    private String content;
    public volatile boolean produce;    // produce 为 true 时，表示可生产

    public Message(boolean produce) {
        this.produce = produce;
    }

    public synchronized void set(String title, String content) {
        if (!produce) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "msg正在生产");
        this.title = title;
        this.content = content;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        produce = false;
        this.notify();
    }

    public synchronized String get() {
        if (produce) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "msg正在获取");
        String res = this.title + "-" + this.content;
        produce = true;
        this.notify();
        return res;
    }
}

class Producer implements Runnable {
    private Message msg;

    public Producer(Message msg) {
        this.msg = msg;
    }

    public void run() {
        for (int x=0; x<30; x++) {
//            System.out.println(Thread.currentThread().getName() + "正在生产");
            if (x % 2 == 0) {
                msg.set("测试1", "测试消息1");
            } else {
                msg.set("测试2", "测试消息2");
            }

        }
    }
}

class Consumer implements Runnable {
    private Message msg;

    public Consumer(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        for (int x=0; x<30; x++) {
            System.out.println(Thread.currentThread().getName() + msg.get());
        }
    }
}