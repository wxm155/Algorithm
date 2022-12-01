package code.slidingwindow;

import java.util.Arrays;

/**
 * @author: wxm
 * @created: 2022/12/01
 */
public class MaxPairNumber {

    /**
     * 给定一个数组arr，代表每个人的能力值。再给定一个非负数k，
     * 如果两个人能力差值正好为k，那么可以凑在一起比赛 一局比赛只有两个人，
     * 返回最多可以同时有多少场比赛
     */

    /**
     * 滑动窗口解法
     *
     * @param arr 能力数组
     * @param k   能力差值
     * @return 最多可以同时进行的场数
     */
    public static int maxPairNum1(int[] arr, int k) {
        if (k < 0 || arr == null || arr.length < 2) {
            return 0;
        }
        Arrays.sort(arr);
        int l = 0, r = 0;
        int len = arr.length;
        int res = 0;
        boolean[] used = new boolean[len];
        while (l < len && r < len) {
            // 使用过
            if (used[l]) {
                l++;
            } else if (l >= r) {
                r++;
            } else {
                int distance = arr[r] - arr[l];
                if (distance == k) {
                    res++;
                    used[r++] = true;
                    l++;
                } else if (distance < k) {
                    r++;
                } else {
                    l++;
                }
            }
        }
        return res;
    }
}
