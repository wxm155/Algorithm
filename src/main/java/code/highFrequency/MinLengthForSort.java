package code.highFrequency;

/**
 * @author wxm
 * @created 2022/11/19
 */
public class MinLengthForSort {

    /**
     * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * 请你找出符合题意的 最短 子数组，并输出它的长度。
     *
     * 示例 1：
     * 输入：nums = [2,6,4,8,10,9,15]
     * 输出：5
     * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     *
     * 示例 2：
     * 输入：nums = [1,2,3,4]
     * 输出：0
     *
     * 示例 3：
     * 输入：nums = [1]
     * 输出：0
     *
     * 提示：
     * 1 <= nums.length <= 104
     * -105 <= nums[i] <= 105
     *
     * 力扣：https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/
     */

    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int len = nums.length;
        int right = -1;
        int max = Integer.MIN_VALUE;
        // 从左往右找到破坏递增的最右位置
        for (int i = 0; i < len; i++) {
            if (max > nums[i]) {
                right = i;
            }
            max = Math.max(max, nums[i]);
        }
        int left = nums.length;
        int min = Integer.MAX_VALUE;
        // 从右往左找到破坏递增的最左位置
        for (int i = len - 1; i >= 0; i--) {
            if (min < nums[i]) {
                left = i;
            }
            min = Math.min(min, nums[i]);
        }
        // 0表示right和left值未发生变化，nums原本就是递增数组
        return Math.max(0, right - left + 1);
    }
}
