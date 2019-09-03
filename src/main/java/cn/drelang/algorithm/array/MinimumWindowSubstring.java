package cn.drelang.algorithm.array;

import java.util.HashMap;

/**
 * leetcode-76: 最小覆盖子串
 *
 * Created by Drelang on 2019/9/2 10:56
 */

public class MinimumWindowSubstring {
}

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minWindow("ADOBECODEBANC", "ABC"));
    }

    public String minWindow(String s, String t) {
        HashMap<Character, Integer> mapT = new HashMap<>();
        for (int i=0; i<t.length(); i++) {
            mapT.merge(t.charAt(i), 1, Integer::sum);
        }

        int requiredSize = mapT.size();

        int left = 0, right = 0;
        int[] res = {left, right, Integer.MAX_VALUE};

        HashMap<Character, Integer> mapS = new HashMap<>();
        mapS.merge(s.charAt(0), 1, Integer::sum);
        while (left <= right) {

            int tmpSize = 0;    // 用来标记是否字符种类数是否与 t 的相同
            for (Character c : mapT.keySet()) {
                if (mapS.containsKey(c) && mapS.get(c) >= mapT.get(c)) {
                    tmpSize++;
                }
            }

            if (tmpSize == requiredSize) {
                if (right - left + 1 < res[2]) {
                    res[0] = left;
                    res[1] = right;
                    res[2] = right - left + 1;
                }
                char c = s.charAt(left);
                mapS.put(c, mapS.get(c) - 1);
                left++;
            } else if (tmpSize < requiredSize) {
                right++;
                if (right >= s.length()) {
                    break;
                } else {
                    mapS.merge(s.charAt(right), 1, Integer::sum);
                }
            } else {
                char c = s.charAt(left);
                mapS.put(c, mapS.get(c) - 1);
                left++;
            }
        }

        return res[2] == Integer.MAX_VALUE ? "" : s.substring(res[0], res[1]+1);
    }
}