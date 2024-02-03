package code.leetCodeTop100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2024/01/26
 */
public class TwoSum {

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     * <p>
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * <p>
     * 示例 2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * <p>
     * 示例 3：
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     * <p>
     * 提示：
     * 2 <= nums.length <= 10^4
     * -109 <= nums[i] <= 10^9
     * -109 <= target <= 10^9
     * 只会存在一个有效答案
     * <p>
     * <p>
     * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
     *
     * 力扣：https://leetcode.cn/problems/two-sum/?envType=study-plan-v2&envId=top-100-liked
     */

    // O(n^2)
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    // O(n)
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
