package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/08/29
 */
public class BobDie {

    /**
     * 给定5个参数，N，M，row，col，k 表示在N*M的区域上，
     * 醉汉Bob初始在(row,col)位置 Bob一共要迈出k步，
     * 且每步都会等概率向上下左右四个方向走一个单位
     * 任何时候Bob只要离开N*M的区域，
     * 就直接死亡 返回k步之后，Bob还在N*M的区域的概率
     */

    // 暴力递归
    public static double bobDie(int N, int M, int row, int col, int k) {
        return (double) process(N, M, row, col, k) / Math.pow(4, k);
    }

    public static long process(int N, int M, int row, int col, int k) {
        // 越界
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        // 还在区域内
        if (k == 0) {
            return 1;
        }
        long right = process(N, M, row + 1, col, k - 1);
        long left = process(N, M, row - 1, col, k - 1);
        long down = process(N, M, row, col + 1, k - 1);
        long up = process(N, M, row, col - 1, k - 1);
        return right + left + down + up;
    }

    // 动态规划
    public static double dp(int N, int M, int row, int col, int k) {
        long[][][] dp = new long[N][M][k + 1];
        // 填充第一层
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        // 每一层都依赖下一层
        for (int l = 1; l <= k; l++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    long right = peek(dp, N, M, i + 1, j, l - 1);
                    long left = peek(dp, N, M, i - 1, j, l - 1);
                    long down = peek(dp, N, M, i, j + 1, l - 1);
                    long up = peek(dp, N, M, i, j - 1, l - 1);
                    dp[i][j][l] = right + left + down + up;
                }
            }
        }
        return (double) dp[row][col][k] / Math.pow(4, k);
    }

    public static long peek(long[][][] dp, int N, int M, int row, int col, int k) {
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        return dp[row][col][k];
    }

    public static void main(String[] args) {
        System.out.println(bobDie(50, 50, 6, 6, 10));
        System.out.println(dp(50, 50, 6, 6, 10));
    }
}
