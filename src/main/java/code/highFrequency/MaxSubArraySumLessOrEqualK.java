package code.highFrequency;

import java.util.TreeSet;

/**
 * @author: wxm
 * @created: 2023/08/17
 */
public class MaxSubArraySumLessOrEqualK {

    /**
     * arr中求子数组的累加和是<=K的并且是最大的，返回这个最大的累加和
     */

    public static int maxSubArraySumLessOrEqualK(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        TreeSet<Integer> set = new TreeSet<>();
        // 一个数也没有的时候，前缀和为0
        set.add(0);
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            Integer pre = set.ceiling(sum - k);
            if (pre != null) {
                ans = Math.max(ans, sum - pre);
            }
            set.add(sum);
        }
        return ans;
    }
}
