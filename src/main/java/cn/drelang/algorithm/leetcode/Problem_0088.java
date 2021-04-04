package cn.drelang.algorithm.leetcode;

/**
 * 扩展，合并三个有序数组
 * 思路：先合并两个，再合并剩下的一个
 *
 * @author Drelang
 * @date 2021/4/1 23:18
 */


public class Problem_0088 {
    // nums1.length = m + n + o
    public void merge(int[] nums1, int m, int[] nums2, int n, int[] nums3, int o) {
        int idx = nums1.length - 1;
        int i = m-1, j = n-1;
        while (i >= 0 && j >= 0) {
            if (nums2[j] > nums1[i]) {
                nums1[idx--] = nums2[j--];
            } else {
                nums1[idx--] = nums1[i--];
            }
        }

        while (i >= 0) {
            nums1[idx--] = nums1[i--];
        }

        while (j >= 0) {
            nums1[idx--] = nums2[j--];
        }
    }
}

