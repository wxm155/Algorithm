package code.sort;

/**
 * @author: wxm
 * @created: 2023/07/06
 */
public class MaxGap {

    /**
     * 给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值 。如果数组元素个数小于 2，则返回 0 。
     * 您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。
     *
     * 示例 1:
     * 输入: nums = [3,6,9,1]
     * 输出: 3
     * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
     *
     * 示例 2:
     * 输入: nums = [10]
     * 输出: 0
     * 解释: 数组元素个数小于 2，因此返回 0。
     *
     * 提示:
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     *
     * 力扣：https://leetcode.cn/problems/maximum-gap/
     */

    /**
     * 时间复杂度：O(N)
     * 设定比数组中的数量多1的桶数量，桶中只包含桶区间的最大值和最小值，
     * 所有数放入后，必存在至少一个空桶
     * 空桶的意义在于不需要去关注每个桶中是否存在解，解一定在跨桶的结果中
     * 注：假设答案法
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            minValue = Math.min(minValue, nums[i]);
            maxValue = Math.max(maxValue, nums[i]);
        }
        if (maxValue == minValue) {
            return 0;
        }
        int bucketLen = len + 1;
        boolean[] hasNum = new boolean[bucketLen];
        int[] min = new int[bucketLen];
        int[] max = new int[bucketLen];
        for (int i = 0; i < len; i++) {
            int index = bucketIndex(nums[i], len, maxValue, minValue);
            min[index] = hasNum[index] ? Math.min(min[index], nums[i]) : nums[i];
            max[index] = hasNum[index] ? Math.max(max[index], nums[i]) : nums[i];
            hasNum[index] = true;
        }
        int ans = 0;
        int preMax = max[0];
        // 获取桶之间的最大值
        for (int i = 1; i < bucketLen; i++) {
            if (hasNum[i]) {
                ans = Math.max(ans, min[i] - preMax);
                preMax = max[i];
            }
        }
        return ans;
    }

    /**
     * 计算放入桶的位置
     *
     * @param num 当前数
     * @param len 数组长度
     * @param max 数组中最大值
     * @param min 数组中最小值
     * @return 桶中的位置
     */
    public static int bucketIndex(long num, long len, long max, long min) {
        return (int) ((num - min) * len / (max - min));
    }
}
