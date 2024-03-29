package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/09/26
 */
public class BurstBalloons {

    /**
     * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
     *  这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
     * 求所能获得硬币的最大数量。
     *
     * 示例 1：
     * 输入：nums = [3,1,5,8]
     * 输出：167
     * 解释：
     * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
     * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
     *
     * 示例 2：
     * 输入：nums = [1,5]
     * 输出：10
     *
     * 力扣：https://leetcode.cn/problems/burst-balloons/
     */

    // 暴力递归
    // 将L...R之间的问题依赖L左边和R右边的情况转换为某个气球最后被打爆的情况
    public static int maxCoins0(int[] nums) {
        // [3,2,1,3]
        // [1,3,2,1,3,1]
        // 数组处理前后加1防止越界条件判断处理
        int[] temp = new int[nums.length + 2];
        for (int i = 0; i < nums.length; i++) {
            temp[i + 1] = nums[i];
        }
        temp[0] = 1;
        temp[temp.length - 1] = 1;
        return process(temp,1,temp.length - 2);
    }

    // L-1位置，和R+1位置，永远不越界，并且，[L-1] 和 [R+1] 一定没爆
    // 返回，arr[L...R]打爆所有气球，最大得分
    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L - 1] * arr[R] * arr[R + 1];
        }
        // L最后被打爆
        int max = process(arr, L + 1, R) + arr[L - 1] * arr[L] * arr[R + 1];
        // R最后被打爆
        max = Math.max(max, process(arr, L, R - 1) + arr[L - 1] * arr[R] * arr[R + 1]);
        for (int i = L + 1; i < R; i++) {
            // i位置气球最后打爆
            int left = process(arr, L, i - 1);
            int right = process(arr, i + 1, R);
            int last = arr[L - 1] * arr[i] * arr[R + 1];
            max = Math.max(max, left + right + last);
        }
        return max;
    }

    // 动态规划解法
    public static int maxCoins1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int len = nums.length;
        int newLen = len + 2;
        int[] arr = new int[newLen];
        for (int i = 0; i < len; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = 1;
        arr[len + 1] = 1;
        int[][] dp = new int[newLen][newLen];
        for (int i = 1; i <= len; i++) {
            dp[i][i] = arr[i - 1] * arr[i] * arr[i + 1];
        }
        // 无法通过四边形不等式优化
        for (int L = len; L >= 1; L--) {
            for (int R = L + 1; R <= len; R++) {
                int max = dp[L + 1][R] + arr[L - 1] * arr[L] * arr[R + 1];
                max = Math.max(max, dp[L][R - 1] + arr[L - 1] * arr[R] * arr[R + 1]);
                for (int i = L + 1; i < R; i++) {
                    max = Math.max(max, arr[L - 1] * arr[i] * arr[R + 1] + dp[L][i - 1] + dp[i + 1][R]);
                }
                dp[L][R] = max;
            }
        }
        return dp[1][len];
    }
}
