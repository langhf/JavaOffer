package cn.drelang.java.advanced.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 字节输出流使用示例
 * Created by Drelang on 2020/05/26 12:46
 */

public class OutputStreamTest {
    public static void main(String[] args) {
        String path = "/Users/drelang/Documents/repository/Java/offer/src/main/java/cn/drelang/java/advanced/io/files";
        File file = new File(path, "output_stream.txt");    // 文件不存在的话，可以自动创建
//        OutputStream output = new FileOutputStream(file);
//        String str = "www.drelang.cn";
//        output.write(str.getBytes());
//        output.close();

        // 使用 AutoCloseable 特性
        try (OutputStream output = new FileOutputStream(file)) {
            String str = "www.drelang.cn";
            output.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

