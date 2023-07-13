package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/07/12
 */
public class MaxArea {

    /**
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     * <p>
     * 示例 1：
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     * <p>
     * 示例 2：
     * 输入：height = [1,1]
     * 输出：1
     * <p>
     * 提示：
     * n == height.length
     * 2 <= n <= 10^5
     * 0 <= height[i] <= 10^4
     * <p>
     * 力扣：https://leetcode.cn/problems/container-with-most-water/
     */

    /**
     * 双指针，谁小移动谁，每次计算矩形的面积，求最大值，
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int L = 0;
        int R = height.length - 1;
        int res = 0;
        while (L < R) {
            res = Math.max(res, Math.min(height[L], height[R]) * (R - L));
            if (height[L] < height[R]) {
                L++;
            } else {
                R--;
            }
        }
        return res;
    }
}
