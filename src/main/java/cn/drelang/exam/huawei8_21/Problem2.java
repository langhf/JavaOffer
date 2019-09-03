package cn.drelang.exam.huawei8_21;

import java.util.Scanner;

/**
 * 给一个区间，左闭右开，求出这个区间内所有素数十位数和，个位数和之间较小的值。
 * 示例：
 *  例1： [1,10） -> 0    0/15
 *  例2： [11, 30) -> 6   8/32
 *  例3： [31, 50) -> 18  18/19
 *
 * Created by Drelang on 2019/9/3 19:56
 */

public class Problem2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int left = sc.nextInt();
            int right = sc.nextInt();
            int h=0, l=0, t;
            for (int i=left; i<right; i++) {
                t = i;
                if (isPrime(t)) {
                    l += t % 10;
                    t /= 10;
                    h += t % 10;
                }
            }
            System.out.println(Math.min(h, l));
        }
    }

    private static boolean isPrime(int n) {
        for (int i=2; i<=Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
