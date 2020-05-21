package cn.drelang.java.advanced;

/**
 * 实现 AutoCloseable 接口，使用 try()-catch 语法时候，可以自动调用对象的 close() 方法
 * Created by Drelang on 2020/05/20 19:39
 */

public class AutoCloseableCase {

    public static void main(String[] args) {
        try (NetMessage nm = new NetMessage("drelang")) {
            nm.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    interface IMessage extends AutoCloseable {
        void send();
    }

    static class NetMessage implements IMessage {

        private String msg;

        public NetMessage(String msg) {
            this.msg = msg;
        }

        public boolean open() {
            System.out.println("【OPEN】获取消息发送连接资源");
            return true;
        }

        @Override
        public void send() {
            if (open()) {
                System.out.println("【***发送消息***】" + this.msg);
            }
        }

        @Override
        public void close() throws Exception {
            System.out.println("【CLOSE】关闭消息发送通道");
        }
    }
}

