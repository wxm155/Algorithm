package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/09/01
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    /**
     * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     * <p>
     * 示例 1：
     * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
     * 输出：8
     * 解释：能够达到的最大利润:
     * 在此处买入 prices[0] = 1
     * 在此处卖出 prices[3] = 8
     * 在此处买入 prices[4] = 4
     * 在此处卖出 prices[5] = 9
     * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
     * <p>
     * 示例 2：
     * 输入：prices = [1,3,7,5,10,3], fee = 3
     * 输出：6
     * <p>
     * 提示：
     * 1 <= prices.length <= 5 * 10^4
     * 1 <= prices[i] < 5 * 10^4
     * 0 <= fee < 5 * 10^4
     * <p>
     * 力扣：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/?envType=daily-question&envId=2023-09-01
     */

    // 在包含冻结期的问题上少了冻结限制，加了每笔手续费限制
    // 每次卖出减去手续费
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int len = prices.length;
        int buy1 = Math.max(-prices[0], -prices[1]);
        int sell1 = Math.max(0, prices[1] - prices[0] - fee);
        for (int i = 2; i < len; i++) {
            buy1 = Math.max(buy1, sell1 - prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i] - fee);
        }
        return sell1;
    }
}
