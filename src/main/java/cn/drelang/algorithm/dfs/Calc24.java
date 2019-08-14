package cn.drelang.algorithm.dfs;

import java.util.Scanner;

/**
 * Created by Drelang on 2019/08/14 11:03
 * 24点运算。原题链接：https://www.nowcoder.com/practice/7e124483271e4c979a82eb2956544f9d?tpId=37&tqId=21312&rp=0&ru=%2Fta%2Fhuawei&qru=%2Fta%2Fhuawei%2Fquestion-ranking&tPage=5
 */

public class Calc24 {

    static int L = 4;
    static boolean[] visited;
    static String formula;  // 运算符号

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] raw = new String[L];
            for (int i=0; i<L; i++) {
                raw[i] = sc.next();
            }
            if (!isValid(raw)) {
                System.out.println("ERROR");
                continue;
            }
            run(raw);
        }
    }

    private static void run(String[] raw) {
        // 在全排列里面查找可能的组合
        int[] arr = new int[L];
        for (int i=0; i<L; i++) {
            arr[i] = str2int(raw[i]);
        }
        // 选取不同的元素作为头元素
        visited = new boolean[L];
        for (int i=0; i<L; i++) {
            visited[i] = true;
            if (dfs(arr[i], 1, arr, raw)) {
                String tmp = raw[i] + formula;
                System.out.println(tmp);
                return;
            }
            visited[i] = false;
        }
        System.out.println("NONE");
    }

    private static boolean dfs(double total, int cnt, int[] arr, String[] raw) {
        if (cnt == 4) {
            formula = "";
            return Double.compare(total, 24.0) == 0;
        } else {
            for (int i=0; i<L; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (dfs(total + arr[i], cnt+1, arr, raw)) {
                        formula = "+" + raw[i] + formula;   // 倒序，因为是递归
                        return true;
                    }
                    if (dfs(total - arr[i], cnt+1, arr, raw)) {
                        formula = "-" + raw[i] + formula;
                        return true;
                    }
                    if (dfs(total * arr[i], cnt+1, arr, raw)) {
                        formula = "*" + raw[i] + formula;
                        return true;
                    }
                    if (dfs(total / arr[i], cnt+1, arr, raw)) {
                        formula = "/" + raw[i] + formula;
                        return true;
                    }
                    visited[i] = false;
                }
            }
            return false;
        }
    }

    private static boolean isValid(String[] raw) {
        for (String s : raw) {
            if (s.equals("joker") || s.equals("JOKER")) {
                return false;
            }
        }
        return true;
    }

    private static int str2int(String s) {
        switch(s) {
            case "A": return 1;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            default:
                return Integer.parseInt(s);
        }
    }

}

