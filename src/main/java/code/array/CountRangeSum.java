package code.array;

/**
 * @author: wxm
 * @created: 2022/03/16
 */
public class CountRangeSum {

    /**
     * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
     * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
     * <p>
     * 示例 1：
     * 输入：nums = [-2,5,-1], lower = -2, upper = 2
     * 输出：3
     * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
     * <p>
     * 示例 2：
     * 输入：nums = [0], lower = 0, upper = 0
     * 输出：1
     * <p>
     * 力扣：https://leetcode-cn.com/problems/count-of-range-sum/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
     *
     * 思路：利用mergesort、有序不回退的特性
     */

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 将数组转换成前缀和数组 ==> [1,2,5,4] ==>[1,3,8,12]
        long[] temp = new long[nums.length];
        temp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            temp[i] = temp[i - 1] + nums[i];
        }
        return process(temp, 0, temp.length - 1, lower, upper);
    }

    public static int process(long[] nums, int left, int right, int lower, int upper) {
        if (left == right) {
            return nums[left] >= lower && nums[left] <= upper ? 1 : 0;
        }
        int mid = left + ((right - left) >> 1);
        int leftResult = process(nums, left, mid, lower, upper);
        int rightResult = process(nums, mid + 1, right, lower, upper);
        int mergeResult = merge(nums, left, mid, right, lower, upper);
        return leftResult + rightResult + mergeResult;
    }

    public static int merge(long[] nums, int left, int mid, int right, int lower, int upper) {

        /**
         * [-2,5,-1]  ==> [-2,3,2]
         * [-2,2] ==> [0,4]
         */
        int result = 0;
        int windowLeft = left;
        int windowRight = left;
        for (int i = mid + 1; i <= right; i++) {
            long max = nums[i] - lower;
            long min = nums[i] - upper;
            // 滑动窗口右指针
            while (windowRight <= mid && nums[windowRight] <= max) {
                windowRight++;
            }
            // 滑动窗口左指针
            while (windowLeft <= mid && nums[windowLeft] < min) {
                windowLeft++;
            }
            result += windowRight - windowLeft;
        }

        // 标准mergesort流程
        long[] tempArr = new long[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= right) {
            tempArr[index++] = nums[p1] < nums[p2] ? nums[p1++] : nums[p2++];
        }
        while (p1 <= mid) {
            tempArr[index++] = nums[p1++];
        }
        while (p2 <= right) {
            tempArr[index++] = nums[p2++];
        }
        for (int i = 0; i < tempArr.length; i++) {
            nums[left + i] = tempArr[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr= {-2,5,-1};
        int rangeSum = countRangeSum(arr, -2, 2);
        System.out.println(rangeSum);
    }
}
