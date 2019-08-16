package cn.drelang.algorithm.dfs;

/**
 * leetcode 124: 二叉树最大路径和
 * 原题链接： https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * 题解： https://blog.csdn.net/ONE_PIECE_HMH/article/details/52661301
 *
 * Created by Drelang on 2019/08/16 09:53
 */

public class BinaryTreeMaxPathSum {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    int max;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        max = Integer.MIN_VALUE;
        find(root);
        return max;
    }

    private int find(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 分支和如果小于零的话，当成 0 来处理
        // 不用 Math.max() 是为了减少内存使用
        int left = find(root.left);
        left = left > 0 ? left : 0;
        int right = find(root.right);
        right = right > 0 ? right : 0;

        // 必须经过 root
        int sum = root.val + left + right;

        if (sum > max) {
            max = sum;
        }

        // 对于上一级，保存该节点和更大的分支
        int more = (left > right) ? left : right;

        return root.val + more;
    }
}

