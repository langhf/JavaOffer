package cn.drelang.java.advanced.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Drelang on 2020/05/29 18:53
 */
class EchoClient {
    private Socket socket;
    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

    public EchoClient(String host, int port) throws Exception {
        socket = new Socket(host, port);
    }

    public void run() {
        try {
            Scanner scan = new Scanner(socket.getInputStream());    // 接收服务端输入内容
            PrintStream out = new PrintStream(socket.getOutputStream());    // 接收服务端输出内容
            scan.useDelimiter("\n");
            boolean flag = true;
            while (flag) {
                String msg = getString("请输入要发送的内容：").trim();
                out.println(msg);
                if (scan.hasNext()) {   // 服务器端有回应
                    System.out.println(scan.next());
                }
                if ("byebye".equalsIgnoreCase(msg)) {
                    flag = false;
                }
            }
            scan.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getString(String prompt) throws Exception {
        System.out.println(prompt);
        return KEYBOARD_INPUT.readLine();
    }

    public static void main(String[] args) throws Exception {
        EchoClient client = new EchoClient("localhost", 520);
        client.run();
    }
}

