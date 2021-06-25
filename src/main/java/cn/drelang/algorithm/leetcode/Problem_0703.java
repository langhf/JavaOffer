package cn.drelang.algorithm.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Drelang
 * @date 2021/5/21 23:19
 */

public class Problem_0703 {

    // 最小堆
    private PriorityQueue<Integer> q;
    private int capacity;

    public Problem_0703(int k, int[] nums) {
        q = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        capacity = k;
        for (Integer num : nums) {
            checkPoll(num);
            q.add(num);
        }
    }

    public int add(int val) {
        checkPoll(val);
        q.add(val);
        return q.peek();
    }

    private void checkPoll(int val) {
        if (q.size() == capacity && val > q.peek()) {
            q.poll();
        }
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = new int[]{4, 5, 8, 2};
        Problem_0703 problem = new Problem_0703(k, arr);
        problem.add(3);
        problem.add(5);
        problem.add(10);
        problem.add(9);
        problem.add(4);
    }
}

