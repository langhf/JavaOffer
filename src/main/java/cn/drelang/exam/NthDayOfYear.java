package cn.drelang.exam;

import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * 输入年月日，返回当天是当年的第几天
 * 参考：https://stackoverflow.com/questions/12525396/today-is-nth-day-of-year
 *
 * Created by Drelang on 2019/9/17 16:30
 */

public class NthDayOfYear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int y = sc.nextInt();
            int m = sc.nextInt();
            int d = sc.nextInt();

            GregorianCalendar gc = new GregorianCalendar();
            gc.set(GregorianCalendar.YEAR, y);
            gc.set(GregorianCalendar.MONTH, m-1);
            gc.set(GregorianCalendar.DAY_OF_MONTH, d);

            System.out.println(gc.get(GregorianCalendar.DAY_OF_YEAR));
        }
    }
}
