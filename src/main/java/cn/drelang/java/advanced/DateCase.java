package cn.drelang.java.advanced;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date 类是对 long 数据的一种包装，里面一堆方法都被 Deprecated。
 * 要想输出人看得懂的日志字符串，需要借助 SimpleDateFormat 类。
 *  SimpleDateFormat 下：
 *      Date parse(String s);
 *      String format(Date d);
 *
 *  String 类型可以向所有其他类型转换，包括数字、大数字、日期等等。
 * Created by Drelang on 2020/05/21 09:46
 */

public class DateCase {
    public static void main(String[] args) {
        Date date = new Date();
        long current = date.getTime();
        current += 3600 * 24 * 10 * 1000; // 10天的毫秒数
        System.out.println(new Date(current));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));

        // 数字格式化
        double money = 123123123.1241234123;
        NumberFormat nf = NumberFormat.getInstance();
        System.out.println(nf.format(money));
    }
}

