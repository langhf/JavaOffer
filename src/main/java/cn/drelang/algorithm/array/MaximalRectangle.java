package cn.drelang.algorithm.array;

import java.util.Stack;

/**
 * leetcode-85: https://leetcode-cn.com/problems/maximal-rectangle/
 * Created by Drelang on 2020/01/30 13:26
 */

public class MaximalRectangle {
    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        MaximalRectangle maximalRectangle = new MaximalRectangle();
        System.out.println(maximalRectangle.maximalRectangle(matrix));  // 6
    }

    public int maximalRectangle(char[][] matrix) {
        int rows, cols;
        if (matrix == null || (rows = matrix.length) < 1 || (cols = matrix[0].length) < 1) {
            return 0;
        }

        int[][] heights = new int[rows][cols];
        // initial
        for (int i=0; i<cols; i++) {
            heights[0][i] = matrix[0][i] == '0' ? 0 : 1;
        }
        for (int i=1; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                heights[i][j] = matrix[i][j] == '0' ? 0 : heights[i-1][j] + 1;
            }
        }

        // 每一行组成一个柱状图，计算柱状图的最大面积，参考 84 题的栈方法
        int max = 0;
        for (int i=0; i<rows; i++) {
            max = Math.max(max, maxArea(heights[i]));
        }

        return max;
    }

    private int maxArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        stack.push(-1);
        int peek;
        for (int i=0; i<n; i++) {
            while ((peek = stack.peek()) != -1 && heights[i] < heights[peek]) {
                stack.pop();
                max = Math.max(max, heights[peek] * (i - stack.peek() -1));
            }
            stack.push(i);
        }
        // 此时到达 n
        while ((peek = stack.peek()) != -1) {
            stack.pop();
            max = Math.max(max, heights[peek] * (n - stack.peek() -1));
        }

        return max;
    }
}

