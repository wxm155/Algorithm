package code.highFrequency;

/**
 * @author: wxm
 * @created: 2022/11/11
 */
public class LongestIncreasingPath {

    /**
     * 给定一个 m x n 整数矩阵matrix ，找出其中最长递增路径的长度。
     * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
     *
     * 示例 1：
     * 输入：matrix = [[9,9,4],
     *                [6,6,8],
     *                [2,1,1]]
     * 输出：4
     * 解释：最长递增路径为 [1, 2, 6, 9]。
     *
     * 示例 2：
     * 输入：matrix = [[3,4,5],
     *                [3,2,6],
     *                [2,2,1]]
     * 输出：4
     * 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
     *
     * 示例 3：
     * 输入：matrix = [[1]]
     * 输出：1
     *
     * 力扣：https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/description/
     *
     */


    // 暴力解
    public static int longestIncreasingPath1(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, process1(matrix, i, j));
            }
        }
        return max;
    }

    public static int process1(int[][] matrix, int i, int j) {
        int up = i > 0 && matrix[i - 1][j] > matrix[i][j] ? process1(matrix, i - 1, j) : 0;
        int down = i < matrix.length - 1 && matrix[i + 1][j] > matrix[i][j] ? process1(matrix, i + 1, j) : 0;
        int left = j > 0 && matrix[i][j - 1] > matrix[i][j] ? process1(matrix, i, j - 1) : 0;
        int right = j < matrix[0].length - 1 && matrix[i][j + 1] > matrix[i][j] ? process1(matrix, i, j + 1) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    // 添加缓存
    public static int longestIncreasingPath2(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int max = 0;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, process2(matrix, i, j, dp));
            }
        }
        return max;
    }

    public static int process2(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int up = i > 0 && matrix[i - 1][j] > matrix[i][j] ? process2(matrix, i - 1, j, dp) : 0;
        int down = i < matrix.length - 1 && matrix[i + 1][j] > matrix[i][j] ? process2(matrix, i + 1, j, dp) : 0;
        int left = j > 0 && matrix[i][j - 1] > matrix[i][j] ? process2(matrix, i, j - 1, dp) : 0;
        int right = j < matrix[0].length - 1 && matrix[i][j + 1] > matrix[i][j] ? process2(matrix, i, j + 1, dp) : 0;
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }
}
