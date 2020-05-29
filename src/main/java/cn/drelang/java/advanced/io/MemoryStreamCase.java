package cn.drelang.java.advanced.io;

import java.io.*;

/**
 * 内存操作流。
 * ByteArrayInputStream, ByteArrayOutputStream
 * ByteArrayStream 现在使用得较少，因为有更好的方法了。
 * Created by Drelang on 2020/05/28 14:55
 */

public class MemoryStreamCase {
    public static void main(String[] args) throws IOException {
        String str = "www.drelang.cn";
        InputStream input = new ByteArrayInputStream(str.getBytes());   // 将数据保存在内存流
        ByteArrayOutputStream output = new ByteArrayOutputStream();  // 读取内存中的数据
        int data = 0;
        while ((data = input.read()) != -1) {
            output.write(Character.toUpperCase(data));
        }
        System.out.println(output); // ByteArrayOutputStream 自带 toString() 方法
        byte[] result = output.toByteArray();   // 获取全部数据
        System.out.println(new String(result));
        input.close();
        output.close();

    }
}

