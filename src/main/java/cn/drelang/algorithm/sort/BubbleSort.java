package cn.drelang.algorithm.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 冒泡排序
 * 每次扫描数组，交换顺序错误的两个数，直到数组排好序
 * Created by Drelang on 2019/8/30 15:13
 */

public class BubbleSort {
    public static void sort(int[] arr) {
        int n;
        if (arr == null || ( n = arr.length) < 1) {
            return;
        }
        // 若一次扫描过程中发生了交换，则说明还未排好序
        for (int i=0; i<n; i++) {
            boolean change = false;
            for (int j=0; j<n-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int t = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                    change = true;
                }
            }
            if (!change) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 4, 0, 1,9};
        int[] arr1 = {44, 22, 1, 2, 4, 5, 4, 1};

        sort(arr);
        sort(arr1);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
    }
}
