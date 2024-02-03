package code.leetCodeTop100;

/**
 * @author: wxm
 * @created: 2024/01/30
 */
public class MaxSubArray {

    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     * <p>
     * 示例 1：
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * <p>
     * 示例 2：
     * 输入：nums = [1]
     * 输出：1
     * <p>
     * 示例 3：
     * 输入：nums = [5,4,-1,7,8]
     * 输出：23
     * <p>
     * 提示：
     * 1 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     * <p>
     * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
     * <p>
     * 力扣：https://leetcode.cn/problems/maximum-subarray/description/?envType=study-plan-v2&envId=top-100-liked
     */


    public int maxSubArray(int[] nums) {
        int preMin = 0;
        int sum = 0;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            res = Math.max(res, sum - preMin);
            preMin = Math.min(preMin, sum);
        }
        return res;
    }
}
