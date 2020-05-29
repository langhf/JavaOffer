package cn.drelang.java.advanced.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Drelang on 2020/05/26 22:54
 */

public class ReaderTest {
    public static void main(String[] args) {
        String path = "/Users/drelang/Documents/repository/Java/offer/src/main/java/cn/drelang/java/advanced/io/files";
        File file = new File(path, "writer.txt");    // 文件不存在的话，可以自动创建

        try (FileReader fileReader = new FileReader(file)) {
            char[] data = new char[1024];
            int n = fileReader.read(data);
            System.out.println("长度：" + n + ", 内容:" + String.valueOf(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

