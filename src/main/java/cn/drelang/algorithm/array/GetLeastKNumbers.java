package cn.drelang.algorithm.array;

import java.util.ArrayList;

/**
 * Created by Drelang on 2019/08/21 08:17
 */

public class GetLeastKNumbers {
    public ArrayList<Integer> solve(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length < k || k < 1) {
            return list;
        }

        int begin = 0, end = input.length-1;
        int index = partition(input, begin, end);
        // index 左边都比 index 处小, 一定要判断 index 和 k-1，而不是 k。因为判断 k 的话，可能 index+1 导致下标溢出异常。
        while (index != k-1) {
            if (index < k-1) {
                begin = index+1;
            } else {
                end = index-1;
            }
            index = partition(input, begin, end);
        }
        for (int i=0; i<k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    private int partition(int[] arr, int begin, int end) {
        if (end == begin) {
            return begin;
        }
        int r = arr[begin];
        while (begin < end) {
            while (begin < end && arr[end] > r) {
                end--;
            }
            swap(arr, begin, end);
            while (begin < end && arr[begin] < r) {
                begin++;
            }
            swap(arr, begin, end);
        }
        return begin;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

