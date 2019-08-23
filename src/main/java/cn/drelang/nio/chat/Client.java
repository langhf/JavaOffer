package cn.drelang.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

/**
 * 基于 NIO 的聊天室客户端
 *
 * Created by Drelang on 2019/08/23 17:12
 */

public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8889));
        socketChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        Scanner sc = new Scanner(System.in);

        while (true) {
            while (sc.hasNext()) {
                String str = sc.nextLine();
                buffer.put((new Date() + "\n" + str).getBytes());
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
            }
        }
    }
}