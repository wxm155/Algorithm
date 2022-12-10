package code.highFrequency;

import java.util.Arrays;

/**
 * @author wxm
 * @created 2022/12/10
 */
public class ClosestSubsequenceSum {

    /**
     * 给你一个整数数组nums和一个目标值goal 。
     * 你需要从nums中选出一个子序列，使子序列元素总和最接近goal 。也就是说，如果子序列元素和为sum ，你需要 最小化绝对差 abs(sum - goal) 。
     * 返回 abs(sum - goal)可能的最小值 。
     * 注意，数组的子序列是通过移除原始数组中的某些元素（可能全部或无）而形成的数组。
     *
     * 示例 1：
     * 输入：nums = [5,-7,3,5], goal = 6
     * 输出：0
     * 解释：选择整个数组作为选出的子序列，元素和为 6 。
     * 子序列和与目标值相等，所以绝对差为 0 。
     *
     * 示例 2：
     * 输入：nums = [7,-9,15,-2], goal = -5
     * 输出：1
     * 解释：选出子序列 [7,-9,-2] ，元素和为 -4 。
     * 绝对差为 abs(-4 - (-5)) = abs(1) = 1 ，是可能的最小值。
     *
     * 示例 3：
     * 输入：nums = [1,2,3], goal = -7
     * 输出：7
     *
     * 提示：
     * 1 <= nums.length <= 40
     * -10^7 <= nums[i] <= 10^7
     * -10^9 <= goal <= 10^9
     *
     * 力扣：https://leetcode.cn/problems/closest-subsequence-sum/
     */


    /**
     * 根据题目的数据提示，goal为10^9大于10^8，动态规划一定过不了
     * 只能根据分治的思想求解
     * @param nums 原始数组
     * @param goal 目标值
     * @return abs(sum - goal)可能的最小值
     */
    public static int minAbsDifference(int[] nums, int goal) {
        if (nums == null || nums.length == 0) {
            return goal;
        }
        // 数组的数据量为1...40,按照要或不要总共有2^40的数量级
        // 左边所有可能累加和数组
        int[] left = new int[1 << 20];
        // 右边所有可能累加和数组
        int[] right = new int[1 << 20];

        int mid = nums.length >> 1;
        int le = process(nums, 0, mid, 0, 0, left);
        int re = process(nums, mid, nums.length, 0, 0, right);

        Arrays.sort(left, 0, le);
        Arrays.sort(right, 0, re--);
        // left[]和right[]一定包含等于0的累加和，就只从另一边找
        // 即包含两边单独查找的过程
        int ans = Math.abs(goal);
        for (int i = 0; i < le; i++) {
            int rest = goal - left[i];
            while (re > 0 && Math.abs(rest - right[re - 1]) <= Math.abs(rest - right[re])) {
                re--;
            }
            ans = Math.min(ans, Math.abs(rest - right[re]));
        }
        return ans;
    }

    /**
     * 将nums[index ... end)的任意数字累加和填在arr中
     * @param nums  原始数组
     * @param index 当前原始数组下标
     * @param end   原始数组结束下标
     * @param fill  累加和填在arr中的位置
     * @param sum   累加和
     * @param arr   累加和数组
     * @return arr填值的结束位置
     */
    public static int process(int[] nums, int index, int end, int fill, int sum, int[] arr) {
        if (index == end) {
            arr[fill++] = sum;
        } else {
            // 不要当前值
            fill = process(nums, index + 1, end, fill, sum, arr);
            // 要当前值
            fill = process(nums, index + 1, end, fill, sum + nums[index], arr);
        }
        return fill;
    }

}
