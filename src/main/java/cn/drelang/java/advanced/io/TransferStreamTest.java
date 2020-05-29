package cn.drelang.java.advanced.io;

import java.io.*;

/**
 * 转换流。
 * InputStreamReader, OutputStreamWriter
 * 实际上 FileReader 继承于 InputStreamReader，FileWriter 继承于 OutputStreamWriter
 * Created by Drelang on 2020/05/28 11:19
 */

public class TransferStreamTest {
    public static void main(String[] args) {
        String path = "/Users/drelang/Documents/repository/Java/offer/src/main/java/cn/drelang/java/advanced/io/files";
        File file = new File(path, "writer.txt");    // 文件不存在的话，可以自动创建
        try (OutputStream output = new FileOutputStream(file, true)) {
            // 相当于包装原来的字节流成字符流，增加了字符输出能力
            Writer out = new OutputStreamWriter(output);
            out.write("hhhhhhhhh\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

