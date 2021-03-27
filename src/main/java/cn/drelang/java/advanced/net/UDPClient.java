package cn.drelang.java.advanced.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Drelang on 2020/05/29 22:45
 */

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket(9999);
        byte[] data = new byte[1024];   // 接收消息
        DatagramPacket packet = new DatagramPacket(data, data.length);  // 接收数据
        System.out.println("客户端等待接收消息....");
        client.receive(packet);
        System.out.println("接收到的消息内容为：" + new String(data, 0, packet.getLength()));
        client.close();
    }
}

