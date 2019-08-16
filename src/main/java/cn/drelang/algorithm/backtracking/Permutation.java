package cn.drelang.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * 字符串的全排列
 * 牛科网原题： <link>https://www.nowcoder.com/practice/fe6b651b66ae47d7acce78ffdd9a96c7?tpId=13&tqId=11180&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking </link>
 * 问题分析：<link>https://www.cnblogs.com/cxjchen/p/3932949.html</link>
 *
 * Created by Drelang on 2019/08/16 07:59
 */

public class Permutation {

    public ArrayList<String> permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return res;
        }
        char[] chs = str.toCharArray();
        Permutation(chs, 0, res);
        Collections.sort(res);
        return res;
    }

    private void Permutation(char[] chs, int begin, ArrayList<String> res) {
        if (begin == chs.length-1) {
            res.add(String.valueOf(chs));
            return ;
        } else {
            HashSet<Character> set = new HashSet<>();   // 去重辅助容器
            for (int i=begin; i<chs.length; i++) {
                if (!set.contains(chs[i])) {
                    set.add(chs[i]);
                    swap(chs, begin, i);
                    Permutation(chs, begin+1, res);
                    swap(chs, begin, i);
                }
            }
        }
    }

    private void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}

