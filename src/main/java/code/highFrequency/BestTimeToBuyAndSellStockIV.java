package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/08/29
 */
public class BestTimeToBuyAndSellStockIV {

    /**
     * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 示例 1：
     * 输入：k = 2, prices = [2,4,1]
     * 输出：2
     * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
     * <p>
     * 示例 2：
     * 输入：k = 2, prices = [3,2,6,5,0,3]
     * 输出：7
     * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
     * <p>
     * 提示：
     * <p>
     * 1 <= k <= 100
     * 1 <= prices.length <= 1000
     * 0 <= prices[i] <= 1000
     * <p>
     * 力扣：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
     */

    public int all(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k < 1) {
            return 0;
        }
        int len = prices.length;
        // k大于等于len / 2 即为买卖次数无限制,买一次卖一次最多的次数为len / 2
        if (k >= len / 2) {
            return all(prices);
        }
        // dp[i][j] => prices[0...i]上完成j次的最大利润
        int[][] dp = new int[len][k + 1];
        // 第一行 dp[0][0...k]在prices[0]完成0...k次交易利润为0
        // 第一列 dp[0...prices.length - 1][0] 在prices[0...prices.length - 1]完成0次交易利润为0
        for (int j = 1; j <= k; j++) {
            // 在dp[1][j]
            // 当前不卖出
            int p1 = dp[0][j];
            // 当前卖出
            // int p2 = dp[1][j - 1] + prices[1] - prices[1];
            // int p3 = dp[0][j - 1] + prices[1] - prices[0];
            // dp[1][j] = Math.max(p1, Math.max(p2, p3));
            int best = Math.max(dp[1][j - 1] - prices[1], dp[0][j - 1] - prices[0]);
            dp[1][j] = Math.max(p1, best + prices[1]);
            for (int i = 2; i < len; i++) {
                // dp[i][j]
                // 当前卖出
                // dp[5][3]的值为：在prices[8]位置卖出，枚举每一次买入时机，取最大值
                // dp[5][2] + prices[5] - prices[5]
                // dp[4][2] + prices[5] - prices[4]
                // dp[3][2] + prices[5] - prices[3]
                // dp[2][2] + prices[5] - prices[2]
                // dp[1][2] + prices[5] - prices[1]
                // dp[0][2] + prices[5] - prices[0]
                // dp[6][3]的值为：
                // dp[6][2] + prices[6] - prices[6]
                // dp[5][2] + prices[6] - prices[5]
                // dp[4][2] + prices[6] - prices[4]
                // dp[3][2] + prices[6] - prices[3]
                // dp[2][2] + prices[6] - prices[2]
                // dp[1][2] + prices[6] - prices[1]
                // dp[0][2] + prices[6] - prices[0]
                // => dp[6][3] = Math.max(dp[5][3] - prices[5],dp[6][2])
                // 当前不卖出
                p1 = dp[i - 1][j];
                // 当前卖出的较上一步多的值
                // 例：dp[6][3]减去prices[6]较dp[5][3]减去prices[5]多出的dp[6][2] - prices[6]
                int p2 = dp[i][j - 1] - prices[i];
                // 多出的部分与之前最好的卖出收益比较
                best = Math.max(p2, best);
                // 最后将prices[i]加上比较最大收益
                dp[i][j] = Math.max(p1, best + prices[i]);
            }
        }
        return dp[len - 1][k];
    }

    public int all(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int max = 0;
        // 在每次波谷的时候买入，波峰的时候卖出为能获得的最大利润
        // 上坡时候的差值相加，下坡的时候差值加0，最终结果即为最大利润
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i - 1];
            max += Math.max(profit, 0);
        }
        return max;
    }
}
