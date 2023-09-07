package code.highFrequency;

import code.dynamicProgramming.SmallestUnFormedSum;

/**
 * @author: wxm
 * @created: 2023/09/06
 */
public class MinPatches {

    /**
     * 给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，
     * 使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示，
     * 请输出满足上述要求的最少需要补充的数字个数
     * <p>
     * 类似题目：{@link SmallestUnFormedSum}
     */

    public static int minPatches(int[] nums, int n) {
        // 任意数字之和能满足的范围
        int range = 0;
        int patches = 0;
        for (int i = 0; i < nums.length; i++) {
            // 搞定range满足1...arr[i]-1范围
            while (nums[i] - 1 > range) {
                range += range + 1;
                patches++;
                // 提前检查
                if (range >= n) {
                    return patches;
                }
            }
            // 加上当前数
            range += nums[i];
            if (range >= n) {
                return patches;
            }
        }
        // 数组的数用完了还没满足条件
        while (n >= range + 1) {
            range += range + 1;
            patches++;
        }
        return patches;
    }
}
