package cn.drelang.java.advanced.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Drelang on 2020/05/26 13:23
 */

public class InputStreamTest {
    public static void main(String[] args) {
        String path = "/Users/drelang/Documents/repository/Java/offer/src/main/java/cn/drelang/java/advanced/io/files";
        File file = new File(path, "output_stream.txt");    // 文件不存在的话，可以自动创建
        byte[] data = new byte[1024];   // 开辟一个缓冲区读取数据

        try (InputStream input = new FileInputStream(file)) {
            int len = input.read(data); // 返回的是读取内容的字节数
            System.out.println(new String(data, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

