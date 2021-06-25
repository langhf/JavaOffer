package cn.drelang.algorithm.leetcode;

import java.util.Objects;

/**
 *
 * @author Drelang
 * @date 2021/5/13 22:58
 */

public class Problem_1095 {
    static class MountainArray {
        int[] arr;

        MountainArray(int[] arr) {
            Objects.requireNonNull(arr);
            this.arr = arr;
        }

        int length() {
            return arr.length;
        }

        int get(int idx) {
            check(idx);
            return arr[idx];
        }

        private void check(int idx) {
            if (idx < 0 || idx >= length()) {
                throw new RuntimeException("超出范围");
            }
        }

    }

    // 二分查找，找到山顶
    // 先去左边找，没找到再去右边找
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int topIdx = findMountainTop(mountainArr);
        int res = findTarget(0, topIdx, target, true, mountainArr);
        if (res == -1) {
            res = findTarget(topIdx, mountainArr.length()-1, target, false, mountainArr);
        }
        return res;
    }

    // 通过 mid 和 mid+1来找山顶
    private int findMountainTop(MountainArray mountainArr) {
        int l = 0, r = mountainArr.length()-1;
        while (l < r) {
            int mid = (l+r) >> 1;
            int midt = mountainArr.get(mid);
            int midpt = mountainArr.get(mid+1);
            if (midt < midpt) {
                l = mid+1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    /**
     *
     * @param l 左边界
     * @param r 右边界
     * @param target 要寻找的数
     * @param up true: 上升边， false：下降边
     */
    private int findTarget(int l, int r, int target, boolean up, MountainArray mountainArr) {
        while (l <= r) {
            int mid = (l + r) >> 1;
            int t = mountainArr.get(mid);
            if (t < target) {
                if (up) {
                    l = mid+1;
                } else {
                    r = mid-1;
                }
            } else if (t == target) {
                return mid;
            } else {
                if (up) {
                    r = mid-1;
                } else {
                    l = mid+1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Problem_1095 solution = new Problem_1095();
//        int[] arr = new int[]{1, 2, 3, 5, 7, 11, 6, 5, 3, 2};
        int[] arr = new int[]{1, 5, 2};
//        int[] arr = new int[]{1, 2, 3, 5, 3};
        MountainArray array = new MountainArray(arr);
        System.out.println(solution.findInMountainArray(7, array));

    }
}

