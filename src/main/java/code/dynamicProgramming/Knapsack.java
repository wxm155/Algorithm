package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/08/04
 */
public class Knapsack {

    /**
     * 给定两个长度都为N的数组weights和values，
     * weights[i]和values[i]分别代表 i号物品的重量和价值
     * 给定一个正数bag，表示一个载重bag的袋子，
     * 装的物品不能超过这个重量 返回能装下的最大价值
     */

    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || bag < 0 || w.length == 0) {
            return 0;
        }
        return process(w, v, 0, bag);
    }

    // index [0...w.length]
    // rest  背包的剩余重量
    public static int process(int[] w, int[] v, int index, int rest) {
        // 可能存在重量为0的物品
        // 先判断重量，再判断index，可能存在rest<0,index等于w.length时将值计算进去
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        // 不要当前物品
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        // 要当前物品
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

    // 动态规划最终解法
    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || bag < 0 || w.length == 0) {
            return 0;
        }
        int len = w.length;
        int[][] dp = new int[len + 1][bag + 1];
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}
