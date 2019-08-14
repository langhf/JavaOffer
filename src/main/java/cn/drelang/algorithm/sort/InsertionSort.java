package cn.drelang.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * Created by Drelang on 2019/08/13 19:58
 */

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr1 = null;
        int[] arr2 = {1, 5, 7, 2, 9, 3, 0};
        int[] arr3 = {5, 4, 3, 2, 1};
        int[] arr4 = {};

        sort(arr1);
        sort(arr2);
        sort(arr3);
        sort(arr4);

        show(arr1);
        show(arr2);
        show(arr3);
        show(arr4);
    }

    private static void sort(int[] array) {
        if (array == null || array.length < 1)
            return ;

        for (int i=1; i<array.length; i++) {
            int tmp = array[i];
            int j = i-1;
            while (j>=0 && array[j] > tmp) {
                array[j+1] = array[j]; // 往后移动一位
                j--;
            }
            array[j+1] = tmp;
        }
    }

    private static void show(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}
