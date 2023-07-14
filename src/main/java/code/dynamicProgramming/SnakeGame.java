package code.dynamicProgramming;

import java.util.Arrays;

/**
 * @author: wxm
 * @created: 2023/07/13
 */
public class SnakeGame {

    /**
     * 给定一个矩阵matrix，值有正、负、0。蛇可以空降到最左列的任何一个位置，初始增长值是0。
     * 蛇每一步可以选择右上、右、右下三个方向的任何一个前进 沿途的数字累加起来，作为增长值；
     * 但是蛇一旦增长值为负数，就会死去。蛇有一种能力，可以使用一次：把某个格子里的数变成相反数
     * 蛇可以走到任何格子的时候停止，返回蛇能获得的最大增长值
     */

    /**
     * 递归解法
     *
     * @param matrix
     * @return
     */
    public static int walk(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Info info = process(matrix, i, j);
                max = Math.max(max, Math.max(info.no, info.yes));
            }
        }
        return max;
    }

    /**
     * 表示蛇走到m[i][j]就停止，所获得增长值
     * 返回一次能力也不用，所获得的最大成长值，
     * 返回用了一次能力，所获得的最大成长值
     * 如果蛇从某一个最左列降落，不用能力，怎么都到不了m[i][j],no等于-1
     * 如果蛇从某一个最左列降落，用能力，怎么都到不了m[i][j],yes等于-1
     * 蛇到达m[i][j]位置的值取决于蛇在m[i][j - 1],m[i - 1][j - 1],m[i + 1][j - 1]位置的值加上或减去当前值
     * 注：动态规划的猜法，要么以当前位置开始，前面的满足指定的情况，要么以当前位置结束，
     *
     * @param m 给定矩阵
     * @param i 行
     * @param j 列
     * @return 蛇获得增长值结果
     */
    public static Info process(int[][] m, int i, int j) {
        // 最左列
        if (j == 0) {
            int no = Math.max(-1, m[i][j]);
            int yes = Math.max(-1, -m[i][j]);
            return new Info(no, yes);
        }
        // 左边的值
        Info left = process(m, i, j - 1);
        int preNo = left.no;
        int preYes = left.yes;
        // 左上的值
        if (i > 0) {
            Info leftUp = process(m, i - 1, j - 1);
            preNo = Math.max(preNo, leftUp.no);
            preYes = Math.max(preYes, leftUp.yes);
        }
        // 左下的值
        if (i < m.length - 1) {
            Info leftDown = process(m, i + 1, j - 1);
            preNo = Math.max(preNo, leftDown.no);
            preYes = Math.max(preYes, leftDown.yes);
        }
        // 不使用能力
        int no = preNo == -1 ? -1 : Math.max(-1, preNo + m[i][j]);
        // 之前就使用过能力
        int p1 = preYes == -1 ? -1 : Math.max(-1, preYes + m[i][j]);
        // 现在使用能力
        int p2 = preNo == -1 ? -1 : Math.max(-1, preNo - m[i][j]);
        return new Info(no, Math.max(p1, p2));
    }

    /**
     * 动态规划解法
     *
     * @param m
     * @return
     */
    public static int walk2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        // dp[i][j][0] => 蛇到达m[i][j]不使用能力获取的最大增长值
        // dp[i][j][1] => 蛇到达m[i][j]使用能力获取的最大增长值
        int[][][] dp = new int[m.length][m[0].length][2];
        for (int i = 0; i < m.length; i++) {
            dp[i][0][0] = Math.max(-1, m[i][0]);
            dp[i][0][1] = Math.max(-1, -m[i][0]);
        }
        for (int j = 1; j < m[0].length; j++) {
            for (int i = 0; i < m.length; i++) {
                int preNo = dp[i][j - 1][0];
                int preYes = dp[i][j - 1][1];
                if (i > 0) {
                    preNo = Math.max(preNo, dp[i - 1][j - 1][0]);
                    preYes = Math.max(preYes, dp[i - 1][j - 1][1]);
                }
                if (i < m.length - 1) {
                    preNo = Math.max(preNo, dp[i + 1][j - 1][0]);
                    preYes = Math.max(preYes, dp[i + 1][j - 1][1]);
                }
                dp[i][j][0] = preNo == -1 ? -1 : Math.max(-1, preNo + m[i][j]);
                int p1 = preYes == -1 ? -1 : Math.max(-1, preYes + m[i][j]);
                int p2 = preNo == -1 ? -1 : Math.max(-1, preNo - m[i][j]);
                dp[i][j][1] = Math.max(p1, p2);
            }
        }
        int max = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
            }
        }
        return max;
    }


    public static class Info {
        // 不使用能力所获得的增长值
        public int no;
        // 使用能力所获得的增长值
        public int yes;

        public Info(int no, int yes) {
            this.no = no;
            this.yes = yes;
        }
    }

    public static int[][] generateRandomArray(int row, int col, int value) {
        int[][] arr = new int[row][col];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int) (Math.random() * value) * (Math.random() > 0.5 ? -1 : 1);
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 7;
        int M = 7;
        int V = 10;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int r = (int) (Math.random() * (N + 1));
            int c = (int) (Math.random() * (M + 1));
            int[][] matrix = generateRandomArray(r, c, V);
            int ans1 = walk(matrix);
            int ans2 = walk2(matrix);
            if (ans1 != ans2) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.println(Arrays.toString(matrix[j]));
                }
                System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
                break;
            }
        }
        System.out.println("nice...");
    }

}
