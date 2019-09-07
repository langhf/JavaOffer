package cn.drelang.algorithm.array;

/**
 * leetcode-84: 柱状图中最大的矩形
 *
 * Created by Drelang on 2019/9/7 11:02
 */

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(solution.largestRectangleArea(heights));
    }

    static class Solution {
        public int largestRectangleArea(int[] heights) {
            int n;
            if (heights == null || (n = heights.length) < 1) {
                return 0;
            }
            return find(heights, 0, n-1);
        }

        private int find(int[] arr, int begin, int end) {
            if (begin <= end) {
                // 找到最低的一块板，以此板作为分隔
                int min = arr[begin], minIndex = begin;
                int i = begin;
                for (; i<=end; i++) {
                    if (arr[i] < min) {
                        min = arr[i];
                        minIndex = i;
                    }
                }
                int area = (end - begin + 1) * min;
                // 以 i 作为分隔
                int left = find(arr, begin, minIndex-1);
                int right = find(arr, minIndex+1, end);
                return Math.max(area, Math.max(left, right));
            }
            return 0;
        }
    }
}

