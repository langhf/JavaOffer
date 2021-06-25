package cn.drelang.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Drelang
 * @date 2021/5/9 22:20
 */

public class Problem_0767 {
    // 1. 出现次数最多的字符数不能超过 n/2 + 1，否则一定不满足条件
    // 2. 出现次数最多的字符放在偶数位置，然后再填充其他字符
    public String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chs = S.toCharArray();
        int threashold = (chs.length + 1) >> 1;
        char mostChar = 'a';
        int mostCount = 0;
        for (Character ch : chs) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
            if (map.get(ch) > mostCount) {
                mostCount = map.get(ch);
                mostChar = ch;
                if (mostCount > threashold) {   // 超过阈值
                    return "";
                }
            }
        }

        // 间隔着填充
        char[] res = new char[chs.length];
        int idx = 0;
        for (int i=0; i<mostCount; i++) {
            res[idx] = mostChar;
            idx += 2;
        }
        // 填充其他的
        map.remove(mostChar);
        for (Character ch : map.keySet()) {
            for (int i=0; i<map.get(ch); i++) {
                if (idx >= chs.length) {    // 需要回到开头
                    idx = 1;
                }
                res[idx] = ch;
                idx += 2;
            }
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        Problem_0767 problem_0767 = new Problem_0767();
        System.out.println(problem_0767.reorganizeString("aab"));
        System.out.println(problem_0767.reorganizeString("aaab"));
        System.out.println(problem_0767.reorganizeString("aaabb"));
        System.out.println(problem_0767.reorganizeString("aabbccdd"));
    }
}

