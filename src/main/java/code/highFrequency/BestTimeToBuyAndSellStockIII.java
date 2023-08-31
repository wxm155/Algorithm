package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/08/31
 */
public class BestTimeToBuyAndSellStockIII {

    /**
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 示例 1:
     * 输入：prices = [3,3,5,0,0,3,1,4]
     * 输出：6
     * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     * <p>
     * 示例 2：
     * 输入：prices = [1,2,3,4,5]
     * 输出：4
     * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * <p>
     * 示例 3：
     * 输入：prices = [7,6,4,3,1]
     * 输出：0
     * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
     * <p>
     * 示例 4：
     * 输入：prices = [1]
     * 输出：0
     * <p>
     * 提示：
     * <p>
     * 1 <= prices.length <= 10^5
     * 0 <= prices[i] <= 10^5
     * <p>
     * 力扣：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
     * <p>
     * 注：{@link BestTimeToBuyAndSellStockIV}
     */

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        if (2 >= len / 2) {
            return all(prices);
        }
        int[][] dp = new int[len][2 + 1];
        for (int j = 1; j <= 2; j++) {
            int p1 = dp[0][j];
            int best = Math.max(dp[1][j - 1] - prices[1], dp[0][j - 1] - prices[0]);
            dp[1][j] = Math.max(p1, best + prices[1]);
            for (int i = 2; i < len; i++) {
                p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1] - prices[i];
                best = Math.max(p2, best);
                dp[i][j] = Math.max(p1, best + prices[i]);
            }
        }
        return dp[len - 1][2];
    }

    public int all(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i - 1];
            max += Math.max(profit, 0);
        }
        return max;
    }
}
