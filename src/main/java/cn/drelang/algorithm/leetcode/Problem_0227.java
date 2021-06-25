package cn.drelang.algorithm.leetcode;

import java.util.Stack;

/**
 *
 * @author Drelang
 * @date 2021/6/23 00:01
 */

public class Problem_0227 {

    // https://leetcode-cn.com/problems/basic-calculator-ii/solution/dan-zhan-jian-ji-xiao-lu-by-cyingenohalt-yjh9/
    // 遇到 +，- 的话，需要强制计算上一个
    public int calculate(String s) {
        int ans = 0;
        char opt = '#';
        boolean positive = true;    // 前一个的加减符号
        char t = 'a';
        int[] num;
        Stack<Integer> stack = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            t = s.charAt(i);
            if (t == '+' || t == '-') { // 累加栈内的值
                if (!stack.isEmpty()) {
                    ans += positive ? stack.pop() : -stack.pop();
                }
                positive = t == '+';
            } else if (t == '*') {  // 计算乘法然后再放进去
                num = tryGetNum(s, i);
                stack.push(stack.pop() * num[0]);
                i = num[1];
            } else if (t == '/') {  // 计算除法再放进去
                num = tryGetNum(s, i);
                stack.push(stack.pop() / num[0]);
                i = num[1];
            } else if (t >= '0' && t <= '9') {
                num = tryGetNum(s, i);
                stack.push(num[0]);
                i = num[1];
            } else {
                continue;
            }
        }
        // 可能还有剩余
        if (!stack.isEmpty()) {
            ans += positive ? stack.pop() : - stack.pop();
        }
        return ans;
    }

    private int[] tryGetNum(String s, int start) {
        int[] res = new int[2];
        int scale = 1;
        while (start < s.length() && s.charAt(start) >= '0' && s.charAt(start) <= '9') {
            res[0] = res[0] * scale + s.charAt(start) - '0';
            scale *= 10;
            start++;
        }
        res[1] = start-1;
        return res;
    }

    public static void main(String[] args) {
        Problem_0227 solution = new Problem_0227();
        System.out.println(solution.calculate("3+2*2"));
    }
}

