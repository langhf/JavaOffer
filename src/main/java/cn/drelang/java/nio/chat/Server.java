package cn.drelang.java.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 基于 NIO 的聊天室的服务端
 *
 * Created by Drelang on 2019/08/23 17:13
 */


public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        ssChannel.configureBlocking(false);

        ssChannel.bind(new InetSocketAddress(8889));

        Selector selector = Selector.open();

        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 轮询式的获取选择器上已经准备就绪的事件
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // 很重要！！！删除已选择的 key，以防重复处理
                iterator.remove();
                // 判断具体是什么事件准备就绪
                if (key.isAcceptable()) {
                    // 接收新客户端，获取客户端连接
                    SocketChannel socketChannel = ssChannel.accept();
                    // 设置为非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 将该通道注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len=0;
                    while ((len = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                }
            }
        }
    }
}