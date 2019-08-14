package cn.drelang.algorithm.sort;

import java.util.Arrays;

/**
 * 快排，时间复杂度 O(N*logN)
 * Created by Drelang on 2019/08/13 20:00
 */


public class QuickSort {
    public static void main(String[] args) {
        int[] arr1 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] arr2 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] arr3 = {1, 5, 3, 7, 43, 12, 32, 32, 12};
        int[] arr4 = null;
        int[] arr5 = {};

        sort(arr1);
        sort(arr2);
        sort(arr3);
        sort(arr4);
        sort(arr5);

        show(arr1);
        show(arr2);
        show(arr3);
        show(arr4);
        show(arr5);
    }

    private static void sort(int[] array) {
        if (array == null || array.length < 1)
            return ;
        sort(array, 0, array.length-1);
    }

    private static void sort(int[] array, int begin, int end) {
        if (begin >= end) { // 注意此处是 >= !!!
            return ;
        }
        int index = partition(array, begin, end);
        sort(array, begin, index-1);
        sort(array, index+1, end);

    }

    private static int partition(int[] array, int begin, int end) {
        int i=begin, j=end, judge=array[begin];
        while (i<j) {
            while (i<j && array[j] >= judge)    // 从右到左寻找比 judge 小的数
                j--;
            swap(array, i, j);
            while (i<j && array[i] <= judge)    // 从左到右寻找比 judge 大的数
                i++;
            swap(array, i, j);
        }
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j)
            return ;
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void show(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
