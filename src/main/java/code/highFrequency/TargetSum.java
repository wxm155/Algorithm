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

    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int len = target + sum;
        int[][] dp = new int[len][len];
        // TODO...
        return 0;
    }
}
