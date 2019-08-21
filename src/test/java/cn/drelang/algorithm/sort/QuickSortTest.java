package cn.drelang.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSortTest {
    @Test
    public void testSort() {
        int[] arr = {6, 2, 7, 3, 8, 9};
        Sort sort = new Sort();
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}