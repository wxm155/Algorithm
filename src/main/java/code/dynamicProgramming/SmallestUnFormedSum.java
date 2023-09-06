package code.dynamicProgramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: wxm
 * @created: 2023/09/04
 */
public class SmallestUnFormedSum {

    /**
     * 给定一个正数数组arr，返回arr的子集不能累加出的最小正数
     * 1、正常怎么做？
     * 2、如果arr中肯定有1这个值，怎么做？
     */

    // 问题一：暴力解
    public static int smallestSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        Set<Integer> set = new HashSet<>();
        process(arr, 0, 0, set);
        for (int min = 1; min != Integer.MIN_VALUE; min++) {
            if (!set.contains(min)) {
                return min;
            }
        }
        return 0;
    }

    public static void process(int[] arr, int index, int pre, Set<Integer> set) {
        if (index == arr.length) {
            set.add(pre);
            return;
        }
        process(arr, index + 1, pre, set);
        process(arr, index + 1, pre + arr[index], set);
    }

    // 问题一：动态规划解法
    public static int smallestSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        int len = arr.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += arr[i];
        }
        // dp[i][j] => 在arr[0...i]上能否累加出j
        boolean[][] dp = new boolean[len][sum + 1];
        // 第一列
        for (int i = 0; i < len; i++) {
            dp[i][0] = true;
        }
        // 第一行
        dp[0][arr[0]] = true;
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= sum; j++) {
                // 不要当前数和要当前数
                dp[i][j] = dp[i - 1][j] || (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]);
            }
        }
        for (int j = 0; j <= sum; j++) {
            if (!dp[len - 1][j]) {
                return j;
            }
        }
        return sum + 1;
    }

    // 问题二
    // arr排完序，利用range范围一直向右扩
    // 在arr[0...i - 1]上，range = 100，arr[i] = 101
    // 此时可以搞定range = 100 + 101范围上的数，
    // 如：搞定160，arr[0...i - 1]上搞定49加上arr[i]的101
    // 若arr[i] = 102时，range无法搞定101的数，即返回range + 1
    public static int smallestSum3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        Arrays.sort(arr);
        int range = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > range + 1) {
                return range + 1;
            } else {
                range += arr[i];
            }
        }
        return range + 1;
    }

    public static int[] generateArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) + 1;
        }
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 27;
        int max = 30;
        int[] arr = generateArray(len, max);
        printArray(arr);
        // arr[0] = 1;
        long start = System.currentTimeMillis();
        System.out.println(smallestSum(arr));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");
        System.out.println("======================================");

        start = System.currentTimeMillis();
        // arr[0] = 1;
        System.out.println(smallestSum2(arr));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");
        System.out.println("======================================");

        System.out.println("set arr[0] to 1");
        arr[0] = 1;
        start = System.currentTimeMillis();
        System.out.println(smallestSum3(arr));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");

    }
}
