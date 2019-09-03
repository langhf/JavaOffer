package cn.drelang.exam.huawei8_21;

import java.util.Scanner;

/**
 * 输入流中遇到 A 替换成 12 和 34， B 转换成 AB 和 CD
 *
 * 输入输出示例：
 *  例1： 4 1 A 2 -> 5 1 12 34 2
 *  例2:  4 A A 3 -> 6 12 34 12 34 3
 *  例3： 11 1 2 3 4 5 6 7 8 9 A -> C 1 2 3 4 5 6 7 8 9 12 34
 *  说明：输入输出第一数字表示流中的数字数量，其中输出的第一个数字必须是 16 进制！！
 *
 * Created by Drelang on 2019/9/3 19:42
 */

public class Problem1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int N = sc.nextInt();
            StringBuffer sb = new StringBuffer();
            String t;
            int len = N-1;
            for (int i=0; i<N-1; i++) {
                t = sc.next();
                if (t.equals("A")) {
                    sb.append("12 ");
                    sb.append("34 ");
                    len++;
                } else if (t.equals("B")) {
                    sb.append("AB ");
                    sb.append("CD ");
                    len++;
                } else {
                    sb.append(t + " ");
                }
            }

            // 注意 Integer.toHexString() 返回的是小写字母
            System.out.println(Integer.toHexString(len+1).toUpperCase() + " " + sb.toString().trim());
        }
    }
}