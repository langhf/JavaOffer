package cn.drelang.java.advanced.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道流。线程之间发送数据。
 * Created by Drelang on 2020/05/28 15:08
 */

public class PipedStreamCase {
    public static void main(String[] args) throws IOException {
        SendThread send = new SendThread();
        ReceiveThread receive = new ReceiveThread();
        send.getOutput().connect(receive.getInput());   // 进行管道连接
        new Thread(send, "消息发送线程").start();
        new Thread(receive, "消息接收线程").start();
    }
}

class SendThread implements Runnable {
    private PipedOutputStream output;

    public SendThread() {
        this.output = new PipedOutputStream();
    }

    @Override
    public void run() {
        for (int x=0; x<10; x++) {
            try {
                this.output.write(("【第" + (x+1) + "信息发送-" + Thread.currentThread().getName() + "】 www.drelang.cn\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PipedOutputStream getOutput() {
        return output;
    }
}

class ReceiveThread implements Runnable {
    private PipedInputStream input;

    public ReceiveThread() {
        this.input = new PipedInputStream();
    }

    @Override
    public void run() {
        byte[] data = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    // 所有数据保存到内存输出流
        try {
            while ((len = this.input.read(data)) != -1) {
                bos.write(data, 0, len);
            }
            System.out.println("{" + Thread.currentThread().getName() + "接收消息}" + new String(bos.toByteArray()));
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PipedInputStream getInput() {
        return input;
    }
}