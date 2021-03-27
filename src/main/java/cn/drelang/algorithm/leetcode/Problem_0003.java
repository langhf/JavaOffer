package cn.drelang.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Drelang
 * @date 2021/3/24 20:18
 */

public class Problem_0003 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("tmmzuxt"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (null == s) return 0;

        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i=-1, j=0; j < s.length(); j++) {
            Character c = s.charAt(j);
            if (!map.containsKey(c) || map.get(c) < i) {
                maxLen = Math.max(maxLen, j - i);
            } else {
                i = map.get(c);
            }
            map.put(c, j);
        }
        return maxLen;
    }
}

