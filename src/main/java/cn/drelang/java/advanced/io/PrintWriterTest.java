package cn.drelang.java.advanced.io;

import java.io.File;
import java.io.PrintWriter;

/**
 * PrintWriter 相当于是对 OutputStream, Writer 的一个封装，类似装饰模式。
 * 比起 OutputStream 类，使用 PrintWriter,PrintStream 类的处理操作会更加简单，因此一般情况下都使用打印流。
 * 只有程序强制使用 OutputStream 的时候，才使用 OutputStream。
 * Created by Drelang on 2020/05/28 17:43
 */

public class PrintWriterTest {
    public static void main(String[] args) throws Exception {
        String path = "/Users/drelang/Documents/repository/Java/offer/src/main/java/cn/drelang/java/advanced/io/files";
        File file = new File(path, "print_writer_test.txt");    // 文件不存在的话，可以自动创建

        PrintWriter pw = new PrintWriter(file);
        String name = "小强子";
        int age = 22;
        double salary = 79128.21312;
        pw.printf("姓名：%s、年龄：%d、收入：%9.2f", name, age, salary);
        pw.close();

    }
}

