package cn.drelang.java.advanced.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Drelang on 2020/05/29 18:53
 */
class EchoServer {
    private ServerSocket serverSocket;

    public EchoServer(int port) throws Exception {
        serverSocket = new ServerSocket(port);
    }

    public void run() throws Exception {
        while (true) {
            System.out.println("等待客户端连接.......");
            Socket client = serverSocket.accept();
            new Thread(()->{
                System.out.println(client.getRemoteSocketAddress());
//                Scanner scan = new Scanner(client.getInputStream());    // 客户端输入流
//                scan.useDelimiter("\n");
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintStream out = new PrintStream(client.getOutputStream());    // 客户端输出流
                    while (true) {
                        String val = in.readLine().trim();
                        if ("byebye".equalsIgnoreCase(val)) {
                            out.println("ByeByeBye...");
                            break;
                        } else {
                            out.println("【ECHO】" + val);
                            System.out.println("收到消息：" + val);
                        }
                    }
                    in.close();
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String[] args) throws Exception {
        EchoServer server = new EchoServer(520);
        server.run();
    }
}

