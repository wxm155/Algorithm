package code.dynamicProgramming;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: wxm
 * @created: 2023/09/04
 */
public class IsSum {

    /**
     * 给定一个有正、有负、有0的数组arr， 给定一个整数k， 返回arr的子集是否能累加出k
     * 1、正常怎么做？
     * 2、如果arr中的数值很大，但是arr的长度不大，怎么做？
     */

    // 问题一：暴力递归解法
    public boolean sum1(int[] arr, int k) {
        if (k == 0) {
            return true;
        }
        if (arr == null || arr.length == 0) {
            return false;
        }
        return process(arr, arr.length - 1, k);
    }

    public static boolean process(int[] arr, int index, int sum) {
        if (sum == 0) {
            return true;
        }
        if (index == -1) {
            return false;
        }
        // 要当前数字
        boolean p1 = process(arr, index - 1, sum);
        // 不要当前数字
        boolean p2 = process(arr, index - 1, sum - arr[index]);
        return p1 || p2;
    }

    // 问题一：动态规划解法
    public boolean sum2(int[] arr, int k) {
        if (k == 0) {
            return true;
        }
        if (arr == null || arr.length == 0) {
            return false;
        }
        int len = arr.length;
        int min = 0, max = 0;
        for (int i = 0; i < len; i++) {
            min += Math.min(arr[i], 0);
            max += Math.max(arr[i], 0);
        }
        if (k < min || k > max) {
            return false;
        }
        // 动态规划表的列逻辑上向右移动了Math.abs(min)个位置
        boolean[][] dp = new boolean[len][max - min + 1];
        // dp[0][0] = true
        dp[0][-min] = true;
        // dp[0][arr[0]] = true
        dp[0][arr[0] - min] = true;
        for (int i = 1; i < len; i++) {
            for (int j = min; j <= max; j++) {
                dp[i][j - min] = dp[i - 1][j - min];
                int next = j - min - arr[i];
                dp[i][j - min] |= (next >= 0 && next <= max - min && dp[i - 1][next]);
            }
        }
        return dp[len - 1][k - min];
    }

    // arr的值很大，动态规划表的列会很多，也会很慢
    // 分治的思想，arr.length不大，哪怕数值很大，分治会比动态规划快
    public boolean sum3(int[] arr, int k) {
        if (k == 0) {
            return true;
        }
        if (arr == null || arr.length == 0) {
            return false;
        }
        if (arr.length == 1) {
            return arr[0] == k;
        }
        int len = arr.length;
        int mid = len >> 1;
        Set<Integer> leftSet = new HashSet<>();
        Set<Integer> rightSet = new HashSet<>();
        process2(arr, 0, mid, 0, leftSet);
        process2(arr, mid, len, 0, rightSet);
        // 例：k = 17
        // 1、左边为0，右边为17
        // 2、左边为17，右边为0
        // 3、左边加右边等于17
        for (Integer left : leftSet) {
            if (rightSet.contains(k - left)) {
                return true;
            }
        }
        return false;
    }

    // 收集arr[start...end - 1]中子集的累加和的所有可能性
    public static void process2(int[] arr, int start, int end, int pre, Set<Integer> set) {
        if (start == end) {
            set.add(pre);
        } else {
            process2(arr, start + 1, end, pre, set);
            process2(arr, start + 1, end, pre + arr[start], set);
        }
    }
}
