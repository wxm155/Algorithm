package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/02/03
 */
public class SubArrayMaxSumFollowUp {

    /**
     * 一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响小偷偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。给定一个代表每个房屋存放金额的非负整数数组 nums，请计算不触动警报装置的情况下，
     * 一夜之内能够偷窃到的最高金额。
     *
     * 示例 1：
     * 输入：nums = [1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     * 偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * 示例 2：
     * 输入：nums = [2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     * 提示：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 400
     *
     * 力扣：https://leetcode.cn/problems/Gu0c2T/
     *
     * 抽象出：给定一个数组arr，在不能取相邻数的情况下，返回所有组合中的最大累加和
     */

    /**
     * 子序列 -> 从左往右
     * dp[i]: 表示dp[0...i]不能取相邻数的最大累加和
     * dp[i]不能取相邻数的最大累加和有三种情况：
     * 1、dp[i] = dp[i - 1]
     * 2、dp[i] = nums[i]
     * 3、dp[i] = dp[i - 2] + nums[i]
     * 三种情况取最大值
     * @param nums 输入数组
     * @return result
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], Math.max(dp[i - 1], nums[i]));
        }
        return dp[nums.length - 1];
    }
}
