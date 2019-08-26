package cn.drelang.exam;

/**
 * 大小为 n 的数组，数组中元素为 1~n-1，有唯一一个重复的数字，请找出来。
 * 要求：时间复杂度为 O(N)，空间复杂度为 O(1)
 *
 * Created by Drelang on 2019/08/26 19:37
 */

public class FindUniquInArray {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 3};
        int[] arr2 = {1, 3, 2, 4, 5, 2};
        System.out.println("find1: arr1  " + find1(arr1));
        System.out.println("find2: arr1  " + find2(arr1));
        System.out.println("find1: arr2  " + find1(arr2));
        System.out.println("find2: arr2  " + find2(arr2));
    }

    // 对数组求和，然后用数学公式求 1~n-1 的和，想减就是结果
    private static int find1(int[] arr) {
        int n = arr.length;
        int sum = n*(n-1)/2;
        for (int i=0; i<n; i++) {
            sum -= arr[i];
        }
        return -sum;
    }

    // 用异或的方法 a ^ b ^ a = b
    // 因为 1<= arr[i] <= n-1, 且 0<=i<=n-1，且 0 ^ x = x。
    // 因此，可以用两个循环去异或 arr[i] 和 i，然后不重复的数一定异或了两次，重复的数一定异或了三次
    private static int find2(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int i=0; i<n; i++) {
            sum ^= arr[i];
        }

        for (int i=0; i<n; i++) {
            sum ^= i;
        }

        // 以上两个 for 其实可以合并到一个里面去，并不影响最终结果
        return sum;
    }

}

