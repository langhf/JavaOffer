package cn.drelang.algorithm.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Drelang on 2019/8/29 23:17
 */
public class GetLeastKNumbersTest {

    @Test
    public void solve() {
        int[] arr = {4, 3, 8, 5, 7, 9, 1};
        int[] arr1 = {88, 23, 2309, 104, 1231, 10293, 12041, 12321, 0};
        int[] arr2 = {3, 3, 2, 2, 1, 4, 8};
        int k = 4;
        System.out.println(Arrays.toString(new GetLeastKNumbers().solve(arr, k).toArray()));
        System.out.println(Arrays.toString(new GetLeastKNumbers().solve(arr1, k).toArray()));
        System.out.println(Arrays.toString(new GetLeastKNumbers().solve(arr2, k).toArray()));
    }
}