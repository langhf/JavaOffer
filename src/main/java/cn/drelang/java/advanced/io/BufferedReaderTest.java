package cn.drelang.java.advanced.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 缓冲字符输入流。
 * 实现键盘数据输入
 * Created by Drelang on 2020/05/28 18:01
 */

public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入信息：");
        String msg = bf.readLine(); // 接收输入信息
        System.out.println("输入内容为：" + msg);
    }
}

