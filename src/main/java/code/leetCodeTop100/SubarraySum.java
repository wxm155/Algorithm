package code.leetCodeTop100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2024/01/30
 */
public class SubarraySum {

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
     * 子数组是数组中元素的连续非空序列。
     * <p>
     * 示例 1：
     * 输入：nums = [1,1,1], k = 2
     * 输出：2
     * <p>
     * 示例 2：
     * 输入：nums = [1,2,3], k = 3
     * 输出：2
     * <p>
     * 提示：
     * 1 <= nums.length <= 2 * 10^4
     * -1000 <= nums[i] <= 1000
     * -10^7 <= k <= 10^7
     */

    public int subarraySum(int[] nums, int k) {
        // key:前缀和 value：前缀和为key的数量
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, count = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
