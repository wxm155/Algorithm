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

    /**
     * 如果当前同学比上一个同学评分高，说明我们就在最近的递增序列中，直接分配给该同学 pre+1个糖果即可。
     * 否则我们就在一个递减序列中，我们直接分配给当前同学一个糖果，并把该同学所在的递减序列中所有的同学都再多分配一个糖果，以保证糖果数量还是满足条件。
     * 我们无需显式地额外分配糖果，只需要记录当前的递减序列长度，即可知道需要额外分配的糖果数量。
     * 同时注意当当前的递减序列长度和上一个递增序列等长时，需要把最近的递增序列的最后一个同学也并进递减序列中。
     * 时间复杂度为O(N),额外空间复杂度为O(1)
     * @param ratings 输入数组
     * @return 返回糖果数
     */
    public static int candy1(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int len = ratings.length;
        // pre 前一个同学得分糖果数
        // inc 升序长度
        // dec 降序长度
        // res 结果
        int pre = 1, inc = 1, dec = 0, res = 1;
        for (int i = 1; i < len; i++) {
            // 升序
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                res += pre;
                inc = pre;
                // 降序
            } else {
                dec++;
                // 最近的升序长度和当前降序长度一样，将升序最后一个并入降序
                if (dec == inc) {
                    dec++;
                }
                res += dec;
                pre = 1;
            }
        }
        return res;
    }
}
