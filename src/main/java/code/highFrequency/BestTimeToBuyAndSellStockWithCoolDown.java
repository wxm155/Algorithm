package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/09/01
 */
public class BestTimeToBuyAndSellStockWithCoolDown {

    /**
     * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 示例 1:
     * 输入: prices = [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     * <p>
     * 示例 2:
     * 输入: prices = [1]
     * 输出: 0
     * <p>
     * 提示：
     * 1 <= prices.length <= 5000
     * 0 <= prices[i] <= 1000
     * <p>
     * 力扣：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/?envType=daily-question&envId=2023-09-01
     */

    // 个别测试案例超时
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        return process(prices, false, 0, 0);
    }

    // isBuy : 是否已经买入
    // buyPrice : 买入的加个
    public static int process(int[] prices, boolean isBuy, int index, int buyPrice) {
        if (index >= prices.length) {
            return 0;
        }
        if (isBuy) {
            int noSell = process(prices, true, index + 1, buyPrice);
            int yesSell = prices[index] - buyPrice + process(prices, false, index + 2, 0);
            return Math.max(noSell, yesSell);
        } else {
            int noBuy = process(prices, false, index + 1, 0);
            int yesBuy = process(prices, true, index + 1, prices[index]);
            return Math.max(noBuy, yesBuy);
        }
    }

    // 记忆化搜索直接过
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        // dp[0][i][j] isBuy为false
        // dp[1][i][j] isBuy为true
        int[][][] dp = new int[2][prices.length + 2][1001];
        return process2(prices, false, 0, 0, dp);
    }

    // isBuy : 是否已经买入
    // buyPrice : 买入的加个
    public static int process2(int[] prices, boolean isBuy, int index, int buyPrice, int[][][] dp) {
        int buy = isBuy ? 1 : 0;
        if (dp[buy][index][buyPrice] != 0) {
            return dp[buy][index][buyPrice];
        }
        if (index >= prices.length) {
            return 0;
        }
        int res = 0;
        if (isBuy) {
            int noSell = process2(prices, true, index + 1, buyPrice, dp);
            int yesSell = prices[index] - buyPrice + process2(prices, false, index + 2, 0, dp);
            res = Math.max(noSell, yesSell);
        } else {
            int noBuy = process2(prices, false, index + 1, 0, dp);
            int yesBuy = process2(prices, true, index + 1, prices[index], dp);
            res = Math.max(noBuy, yesBuy);
        }
        dp[buy][index][buyPrice] = res;
        return res;
    }

    // 动态规划解法
    // buy[i]: 在prices[0...i]上，最后一次操作是买入操作,可能发生在i上，也可能发生在i之前的最好收益
    // sell[i]: 在prices[0...i]上，最后一次操作是卖出操作,可能发生在i上，也可能发生在i之前的最好收益
    // buy[1]: 只能在prices[0]上买入或者prices[1]上买入，之前的收益为0
    // sell[1]: 在prices[0]买入，prices[1]卖出，和不买入卖出取最大值

    // buy[i]: Math.max(buy[i - 1], sell[i - 2] - prices[i])
    // buy[i - 1]: 当前不买入，买入操作发生在前一步
    // sell[i - 2] - prices[i]: 冻结前一步卖出的最好收益减去当前买入消耗的收益

    // sell[i]: Math.max(sell[i - 1], buy[i - 1] + prices[i])
    // sell[i - 1]: 当前不卖出，卖出操作发生在前一步
    // buy[i - 1] + prices[i]: 前一步买入的最好收益加上当前卖出的收益
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        buy[1] = Math.max(-prices[0], -prices[1]);
        sell[1] = Math.max(0, prices[1] - prices[0]);
        for (int i = 2; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[len - 1];
    }

    // 方法三的空间优化，最优解
    public int maxProfit4(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int buy1 = Math.max(-prices[0], -prices[1]);
        int sell1 = Math.max(0, prices[1] - prices[0]);
        int sell2 = 0;
        for (int i = 2; i < len; i++) {
            int temp = sell1;
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy1 = Math.max(buy1, sell2 - prices[i]);
            sell2 = temp;
        }
        return sell1;
    }
}
