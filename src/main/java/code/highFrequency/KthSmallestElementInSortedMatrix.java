package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/09/11
 */
public class KthSmallestElementInSortedMatrix {

    /**
     * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
     * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
     * 你必须找到一个内存复杂度优于 O(n2) 的解决方案。
     * <p>
     * 示例 1：
     * 输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
     * 输出：13
     * 解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
     * <p>
     * 示例 2：
     * 输入：matrix = [[-5]], k = 1
     * 输出：-5
     * <p>
     * 提示：
     * n == matrix.length
     * n == matrix[i].length
     * 1 <= n <= 300
     * -10^9 <= matrix[i][j] <= 10^9
     * 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
     * 1 <= k <= n2
     * <p>
     * 进阶：
     * 你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题?
     * 你能在 O(n) 的时间复杂度下解决这个问题吗?这个方法对于面试来说可能太超前了，但是你会发现阅读这篇文章（ this paper ）很有趣。
     * <p>
     * 力扣：https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/
     * <p>
     * {@link FindNumInSortedMatrix}
     */

    public int kthSmallest(int[][] matrix, int k) {
        int N = matrix.length, M = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[N - 1][M - 1];
        int ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 二分求出小于mid数的个数和小于但最接近mid的数，因为mid不一定存在
            Info info = noMoreNum(matrix, mid);
            // 小于k，区间范围小了
            if (info.num < k) {
                left = mid + 1;
            } else {
                ans = info.near;
                right = mid - 1;
            }
        }
        return ans;
    }

    public static Info noMoreNum(int[][] matrix, int value) {
        int N = matrix.length, M = matrix[0].length;
        int row = 0, col = M - 1;
        int num = 0, near = Integer.MIN_VALUE;
        while (row < N && col >= 0) {
            int cur = matrix[row][col];
            if (cur <= value) {
                near = Math.max(near, cur);
                num += col + 1;
                row++;
            } else {
                col--;
            }
        }
        return new Info(num, near);
    }

    public static class Info {
        public int num;
        public int near;

        public Info(int num, int near) {
            this.num = num;
            this.near = near;
        }
    }
}
