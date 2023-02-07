package code.greed;

/**
 * @author: wxm
 * @created: 2023/02/06
 */
public class CandyProblem {

    /**
     * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
     * 你需要按照以下要求，给这些孩子分发糖果：
     * 每个孩子至少分配到 1 个糖果。
     * 相邻两个孩子评分更高的孩子会获得更多的糖果。
     * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     *
     * 示例 1：
     * 输入：ratings = [1,0,2]
     * 输出：5
     * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
     *
     * 示例 2：
     * 输入：ratings = [1,2,2]
     * 输出：4
     * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
     *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
     *
     * 提示：
     * n == ratings.length
     * 1 <= n <= 2 * 104
     * 0 <= ratings[i] <= 2 * 104
     *
     * 力扣：https://leetcode.cn/problems/candy/description/
     */

    /**
     * 从左往右遍历，处理右边比左边大
     * 从右往左遍历，处理左边比右边大
     * 两次遍历，时间复杂度为O(N),额外空间复杂度为O(N)
     * @param ratings 输入数组
     * @return 返回糖果数
     */
    public static int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int len = ratings.length;
        int[] left = new int[len];
        // 从左往右找出分配糖果数
        for (int i = 0; i < len; i++) {
            left[i] = i > 0 && ratings[i] > ratings[i - 1] ? left[i - 1] + 1 : 1;
        }
        int right = 0, res = 0;
        // 从右往左找出分配糖果数，取左右最大值
        for (int i = len - 1; i >= 0; i--) {
            right = i < len - 1 && ratings[i] > ratings[i + 1] ? right + 1 : 1;
            res += Math.max(left[i], right);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,0,2};
        System.out.println(candy(arr));
    }
}
