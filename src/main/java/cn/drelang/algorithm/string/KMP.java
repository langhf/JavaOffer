package cn.drelang.algorithm.string;

/**
 * KMP 算法
 * Created by Drelang on 2019/9/6 21:00
 */

public class KMP {

    public static void main(String[] args) {
        KMP kmp = new KMP();
        String s = "abcabcabc";
        String p = "cab";
        System.out.print(kmp.kmp(s, p));
    }

    // 核心函数
    private int[] getNext(String pattern) {
        int n = pattern.length();
        char[] p = pattern.toCharArray();
        int k = -1;
        int j = 0;
        int[] next = new int[n];
        next[0] = -1;
        while (j < n-1){
            /*
             * 关键点！
             * 当 p[j] == p[k] 的时候，next[j+1] 直接等于 next[j] + 1 ，也就是 k + 1
             * 当 p[j] != p[k] 的时候，需要回退 k，类似并查集那样，找到父节点，
             *      直到 p[j] == p[k]，或者到 k = -1 (即无法再回退)
             */
            if (k == -1 || p[j] == p[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    int kmp(String s, String pattern) {
        int m, n;
        if (s == null || (m = s.length()) < 1 || pattern == null || (n = pattern.length()) < 1) {
            return -1;
        }
        int[] next = getNext(pattern);
        int i=0, j=0;
        while (i < m && j < n) {

            // j=-1时 j需要回到0，i 也需要向后移动
            // s[i] = p[j] 时，两者都向后移
            if (j == -1 || s.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {    // s[i] != p[j] 时，需要回溯 j
                j = next[j];
            }
        }

        return j == n ? (i-j) : -1;
    }
}