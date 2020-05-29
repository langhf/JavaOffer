package cn.drelang.java.advanced.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * java.util.Scanner
 * Scanner 比 BufferedReader 使用起来更方便，使用的更多！
 * useDelimiter() 可以自定义分隔符。
 * 可以使用正则做判断。
 * Created by Drelang on 2020/05/28 18:10
 */

public class ScannerTest {
    public static void main(String[] args) throws ParseException, FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的生日：");
        if (sc.hasNext("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse(sc.next()));
        } else {
            System.out.println("生日格式有误！");
        }
        sc.close();

        String path = "/Users/drelang/Documents/repository/Java/offer/src/main/java/cn/drelang/java/advanced/io/files";
        File file = new File(path, "test.txt");
        Scanner sc1 = new Scanner(new FileInputStream(file));
        sc1.useDelimiter("\n"); //设置读取分隔符，默认使用的是空格作为分隔符。
        while (sc1.hasNext()) {
            System.out.println(sc1.next());
        }
        sc1.close();
    }
}

