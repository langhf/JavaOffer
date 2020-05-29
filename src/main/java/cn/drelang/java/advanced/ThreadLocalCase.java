package cn.drelang.java.advanced;

/**
 * Created by Drelang on 2020/05/21 15:02
 */

public class ThreadLocalCase {
    public static void main(String[] args) throws InterruptedException {
        Message message = new Message();
        message.setInfo("你好");
        Channel.setMessage(message);
        Channel.send();

        // 以下代码会出现线程和消息不同步的问题
        System.out.println("-----------------------------");
        for (int i=0; i<10; i++) {
            int x = i;
            new Thread(()->{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.setInfo("消息"+x);
                Channel.setMessage(msg);
                Channel.send();
            }, "线程"+x).start();
        }

        Thread.sleep(1000);

        // 使用 ThreadLocal 来改进
        System.out.println("-----------------------------");
        for (int i=100; i<110; i++) {
            int x = i;
            new Thread(()->{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.setInfo("消息"+x);
                Channel1.setThreadLocal(msg);
                Channel1.send();
            }, "线程"+x).start();
        }
    }

    static class Channel {
        private static Message message;

        public static void setMessage(Message m) {
            message = m;
        }

        public static void send() {
            System.out.println(Thread.currentThread().getName() + "【发送消息】:" + message.getInfo());
        }
    }

    static class Channel1 {
        private final static ThreadLocal<Message> THREAD_LOCAL = new ThreadLocal<>();

        public static void setThreadLocal(Message m) {
            THREAD_LOCAL.set(m);
        }

        public static void send() {
            try {
                Message msg = THREAD_LOCAL.get();
                System.out.println(Thread.currentThread().getName() + "【发送消息】:" + msg.getInfo());
            } finally {
                THREAD_LOCAL.remove();
            }

        }
    }

    static class Message {
        private String info;

        public void setInfo(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }
}

