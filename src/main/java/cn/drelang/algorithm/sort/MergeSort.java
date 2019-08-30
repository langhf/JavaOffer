package cn.drelang.algorithm.sort;

import java.util.Arrays;

/**
 * 分治策略，归并排序
 * Created by Drelang on 2019/08/13 19:59
 */


public class MergeSort {
    public static void main(String[] args) {
        int[] arr1 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] arr2 = {9, 8, 7, 6, 5, 4, 3, 2, 8, 4, 1, 0};
        int[] arr3 = {1, 1, 1, 1, 1};
        int[] arr4 = null;
        int[] arr5 = {};

        MergeSort2 mergeSort2 = new MergeSort2();
        mergeSort2.sort(arr1);
        show(arr1);
        mergeSort2.sort(arr2);
        show(arr2);
        mergeSort2.sort(arr3);
        show(arr3);

//        sort(arr1);
//        sort(arr2);
//        sort(arr3);
//        sort(arr4);
//        sort(arr5);
//
//        show(arr1);
//        show(arr2);
//        show(arr3);
//        show(arr4);
//        show(arr5);

    }

    private static void sort(int[] array) {
        if (array == null || array.length <1)
            return ;
        int[] copy = new int[array.length];   // 需要使用一个辅助数组
        doSort(array, copy, 0, array.length-1);
    }

    private static void doSort(int[] array, int[] copy, int start, int end) {
        if (start == end) { // 此处只能是 == ，不能是 >=
            copy[start] = array[start];
            return ;
        }

        int mid = (start + end) >> 1;
        doSort(array, copy, start, mid);
        doSort(array, copy, mid+1, end);

        int i=mid, j=end, index=end;
        while (i>=start && j>=mid+1) {
            // 两边都从右到左比较，将每次最大的填入辅助数组的高位
            if (array[i] > array[j])
                copy[index--] = array[i--];
            else
                copy[index--] = array[j--];
        }
        // 可能有一边还有剩余，但是不可能同时剩余
        while (i>=start) {
            copy[index--] = array[i--];
        }
        while (j>=mid+1) {
            copy[index--] = array[j--];
        }
        // 通过 copy 数组，改变原数组，一定要这一步！！！
        for (int s=start; s<=end; s++) {
            array[s] = copy[s];
        }
    }

    private static void show(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}

/**
 * 练手用
 */
class MergeSort2 {
    void sort(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        int[] copy = new int[arr.length];
        doSort(arr, 0, arr.length-1, copy);
    }

    void doSort(int[] arr, int begin, int end, int[] copy) {
        if (begin < end) {
            int mid = (begin + end) >> 1;
            doSort(arr, begin, mid, copy);
            doSort(arr, mid+1, end, copy);
            int index = end, i = mid, j = end;
            while (i >= begin && j >= mid+1) {
                if (arr[i] > arr[j]) {
                    copy[index--] = arr[i--];
                } else {
                    copy[index--] = arr[j--];
                }
            }

            while (i >= begin) {
                copy[index--] = arr[i--];
            }
            while (j >= mid+1) {
                copy[index--] = arr[j--];
            }

            for (int k=begin; k<=end; k++) {
                arr[k] = copy[k];
            }
        }
    }
}