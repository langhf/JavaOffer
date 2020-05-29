package cn.drelang.java.advanced.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符流使用到缓冲区，积累到一定数据量的时候才输出。如果没有调用 close() 方法，那么可能发生内容还在缓冲区，没有输出的情况。
 *  此时可以调用 flush() 方法，强制缓冲区的内容输出。此外，字符流更适合处理中文信息。
 * 字节流没有使用到缓冲区。
 * Created by Drelang on 2020/05/26 22:45
 */

public class WriterTest {
    public static void main(String[] args) {
        String path = "/Users/drelang/Documents/repository/Java/offer/src/main/java/cn/drelang/java/advanced/io/files";
        File file = new File(path, "writer.txt");    // 文件不存在的话，可以自动创建

        try (FileWriter fileWriter = new FileWriter(file, false)) { // 默认覆盖模式，选择 true 时表示追加
            fileWriter.write("hello");
            fileWriter.append("world");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

