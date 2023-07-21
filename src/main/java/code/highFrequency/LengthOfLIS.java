package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/07/20
 */
public class LengthOfLIS {

    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
     * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * <p>
     * 示例 1：
     * 输入：nums = [10,9,2,5,3,7,101,18]
     * 输出：4
     * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
     * <p>
     * 示例 2：
     * 输入：nums = [0,1,0,3,2,3]
     * 输出：4
     * <p>
     * 示例 3：
     * 输入：nums = [7,7,7,7,7,7,7]
     * 输出：1
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 2500
     * -10^4 <= nums[i] <= 10^4
     * <p>
     * 力扣：https://leetcode.cn/problems/longest-increasing-subsequence/
     */

    /**
     * 动态规划解法：
     * dp[i]表示nums[0...i]中的最长增长子序列的长度
     * 时间复杂度：O(N^2)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j <= i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 动态规划 + 二分解法
     * 将遍历去填充dp[i]的值换做二分查找
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // dp[i]的值代表长度为i+1的子序列尾部元素的值
        int[] dp = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int l = 0, r = max;
            while (l < r) {
                int mid = l + ((r - l) >> 1);
                if (dp[mid] < nums[i]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            dp[l] = nums[i];
            if (r == max) {
                max++;
            }
        }
        return max;
    }
}
