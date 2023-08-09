package code.highFrequency;

import java.util.*;

/**
 * @author: wxm
 * @created: 2023/08/09
 */
public class LongestConsecutive {

    /**
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     *
     * 示例 1：
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     *
     * 示例 2：
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     *
     * 提示：
     * 0 <= nums.length <= 10^4
     * -10^9 <= nums[i] <= 10^9
     *
     * 进阶：可以设计并实现时间复杂度为 O(n) 的解决方案吗？
     *
     * 力扣：https://leetcode.cn/problems/WhsWhI/
     */

    public int longestConsecutive(int[] nums) {
        // key:当前数 value:当前数所能连续的长度
        Map<Integer,Integer> map = new HashMap<>();
        int len = 0;
        for (int num : nums) {
            if (!map.containsKey(num)){
                map.put(num,1);
                int preLen = map.getOrDefault(num - 1,0);
                int posLen = map.getOrDefault(num + 1,0);
                int all = preLen + posLen + 1;
                map.put(num - preLen,all);
                map.put(num + posLen,all);
                len = Math.max(len,all);
            }
        }
        return len;
    }
}
