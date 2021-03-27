package cn.drelang.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Drelang
 * @date 2021/3/25 22:44
 */

public class Problem_0015 {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return null;
        }

        Arrays.sort(nums);
        if (nums[0] > 0) {
            return null;
        }

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int j = i+1, k = nums.length - 1, target = 0 - nums[i];
            while (j < k) {
                int t = nums[j] + nums[k];
                if (t == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    while (j < k && nums[j+1] == nums[j]) { // 避免重复
                        j++;
                    }
                    j++;
                } else if (t < target) {
                    j++;
                } else {
                    k--;
                }
            }

            while (i < nums.length -1 && nums[i] == nums[i+1]) {    // 避免重复
                i++;
            }
        }

        return res;
    }
}

