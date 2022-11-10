package code.highFrequency;

/**
 * @author wxm
 * @created 2022/11/6
 */
public class TargetSum {

    /**
     * 给你一个整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     * 示例 1：
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     *
     * 示例 2：
     * 输入：nums = [1], target = 1
     * 输出：1
     *  
     * 提示：
     * 1 <= nums.length <= 20
     * 0 <= nums[i] <= 1000
     * 0 <= sum(nums[i]) <= 1000
     * -1000 <= target <= 1000
     *
     * 力扣：https://leetcode.cn/problems/target-sum/
     *
     * 转化为背包问题：{@link code.dynamicProgramming.Knapsack}
     */

    // 暴力递归
    public int findTargetSumWays1(int[] nums, int target) {
        return process1(nums,0,target);
    }

    public static int process1(int[] nums,int index,int rest){
        if (index == nums.length){
            return rest == 0 ? 1 : 0;
        }
        // 当前数符号为 + 或 -
        return process1(nums,index + 1,rest + nums[index])
                + process1(nums,index +1,rest - nums[index]);
    }

    // 优化点一 :
    // 你可以认为arr中都是非负数
    // 因为即便是arr中有负数，比如[3,-4,2]
    // 因为你能在每个数前面用+或者-号
    // 所以[3,-4,2]其实和[3,4,2]达成一样的效果
    // 那么我们就全把arr变成非负数，不会影响结果的
    // 优化点二 :
    // 如果arr都是非负数，并且所有数的累加和是sum
    // 那么如果target<sum，很明显没有任何方法可以达到target，可以直接返回0
    // 优化点三 :
    // arr内部的数组，不管怎么+和-，最终的结果都一定不会改变奇偶性
    // 所以，如果所有数的累加和是sum，
    // 并且与target的奇偶性不一样，没有任何方法可以达到target，可以直接返回0
    // 优化点四 :
    // 比如说给定一个数组, arr = [1, 2, 3, 4, 5] 并且 target = 3
    // 其中一个方案是 : +1 -2 +3 -4 +5 = 3
    // 该方案中取了正的集合为P = {1，3，5}
    // 该方案中取了负的集合为N = {2，4}
    // 所以任何一种方案，都一定有 sum(P) - sum(N) = target
    // 现在我们来处理一下这个等式，把左右两边都加上sum(P) + sum(N)，那么就会变成如下：
    // sum(P) - sum(N) + sum(P) + sum(N) = target + sum(P) + sum(N)
    // 2 * sum(P) = target + 数组所有数的累加和
    // sum(P) = (target + 数组所有数的累加和) / 2
    // 也就是说，任何一个集合，只要累加和是(target + 数组所有数的累加和) / 2
    // 那么就一定对应一种target的方式
    // 也就是说，比如非负数组arr，target = 7, 而所有数累加和是11
    // 求有多少方法组成7，其实就是求有多少种达到累加和(7+11)/2=9的方法
    // 优化点五 :
    // 二维动态规划的空间压缩技巧
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum < target || ((sum & 1) ^ (target & 1)) != 0 ? 0 : subset2(nums, (target + sum) >> 1);
    }

    // 动态规划，不使用空间压缩
    // 求非负数组nums有多少个子集，累加和是s
    public static int subset1(int[] nums, int s) {
        if (s < 0) {
            return 0;
        }
        int len = nums.length;
        // dp[i][j] => 在nums[0...i]中的所有子集，累加和为j的数量
        int[][] dp = new int[len + 1][s + 1];
        // 满足累加和为0的所有子集合数量
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= s; j++) {
                dp[i][j] = dp[i - 1][j];
                // 背包问题，背包还可以再放
                if (j - nums[i - 1] >= 0) {
                    // 加上剩余背包空间在nums[0...i-1]上累加和为j - nums[i - 1]的数量
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[len][s];
    }

    // 动态规划，空间压缩
    // 核心就是for循环里面的：for (int i = s; i >= n; i--){
    // 为啥不枚举所有可能的累加和？只枚举 n...s 这些累加和？
    // 因为如果 i < n，dp[i]怎么更新？和上一步的dp[i]一样！不用更新
    // 如果 i >= n，dp[i]怎么更新？上一步的dp[i] + 上一步dp[i - n]的值，这才需要更新
    public static int subset2(int[] nums, int s) {
        if (s < 0) {
            return 0;
        }
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums) {
            // 只更新 dp[n...s]的值
            for (int i = s; i >= n; i--) {
                dp[i] += dp[i - n];
            }
        }
        return dp[s];
    }
}
