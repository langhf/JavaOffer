package cn.drelang.algorithm.leetcode;

/**
 * TODO:
 *
 * @author Drelang
 * @date 2021/3/26 22:50
 */

public class Problem_0415 {

    public static void main(String[] args) {
        System.out.println(addStrings("584", "18"));
    }

    public static String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int c = 0, t = 0;
        int len1 = num1.length(), len2 = num2.length();
        int i=len1-1, j=len2-1;
        for (; i>=0 && j>=0; i--, j--) {
            int a = getNum(num1.charAt(i));
            int b = getNum(num2.charAt(j));
            int tmp = (a+b+c);
            t = tmp % 10;
            res.append(t);
            c = tmp / 10;

        }

        while (i >= 0) {
            int a = getNum(num1.charAt(i));
            res.append((a+c) % 10);
            c = (a+c) / 10;
            i--;
        }

        while (j >= 0) {
            int b = getNum(num2.charAt(j));
            res.append((b+c) % 10);
            c = (b+c) / 10;
            j--;
        }

        if (c != 0) {
            res.append(c);
        }

        return res.reverse().toString();
    }

    private static int getNum(char in) {
        return in - '0';
    }
}

