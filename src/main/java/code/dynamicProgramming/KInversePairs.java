package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/07/27
 */
public class KInversePairs {

    /**
     * 逆序对的定义如下：对于数组 nums 的第 i 个和第 j 个元素，如果满足 0 <= i < j < nums.length 且 nums[i] > nums[j]，则其为一个逆序对；否则不是。
     * 给你两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个 逆序对 的不同的数组的个数。由于答案可能很大，只需要返回对 10^9 + 7 取余的结果。
     * <p>
     * 示例 1：
     * 输入：n = 3, k = 0
     * 输出：1
     * 解释：
     * 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
     * <p>
     * 示例 2：
     * 输入：n = 3, k = 1
     * 输出：2
     * 解释：
     * 数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
     * <p>
     * 提示：
     * 1 <= n <= 1000
     * 0 <= k <= 1000
     * <p>
     * 力扣：https://leetcode.cn/problems/k-inverse-pairs-array/
     */

    /**
     * 根据数据量提示需要O(N) * O(K)时间复杂度的算法，即可想出dp[n][k]的动态规划表
     *
     * @param n
     * @param k
     * @return
     */
    public int kInversePairs(int n, int k) {
        if (n < 0 || k < 0) {
            return 0;
        }
        // dp[i][j] => 1...n的数字组成j个逆序对的数组个数
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;
        int mod = 1000000007;
        for (int i = 1; i <= n; i++) {
            // 全是升序的，组成0个逆序对的数组个数
            dp[i][0] = 1;
            for (int j = 1; j <= k; j++) {
                // 当j < i时
                // 例：i = 5，j = 3;
                // 当i在最后一位的时候 => 12345，需要i = 4时组成j = 3个逆序对 => dp[4][3]
                // 当i在倒数第二位的时候 => 12354，需要i = 4时组成j = 2个逆序对，5在倒数第二位时最后两位必组成一个逆序对 => dp[3][2]
                // 依此类推
                // 即 dp[5][3] = dp[4][3] + dp[4][2] + dp[4][1] + dp[4][0]
                // 同理：dp[5][4] = dp[4][4] + dp[4][3] + dp[4][2] + dp[4][1] + dp[4][0]
                // 得出：dp[5][4] = dp[4][4] + dp[5][3]
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % mod;
                // 当j >= i 时
                // 例：i = 5，j = 7;
                // dp[5][7] = dp[4][7] + dp[4][6] + dp[4][5] + dp[4][4] + dp[4][3] 当5移动到最开始的位置时将无法移动
                // dp[5][8] = dp[4][8] + dp[4][7] + dp[4][6] + dp[4][5] + dp[4][4]
                // 得出：dp[5][8] = dp[4][8] + dp[5][7] - dp[4][3]
                if (j >= i) {
                    // dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - i];
                    // + mod防止变负数
                    dp[i][j] = (dp[i][j] - dp[i - 1][j - i] + mod) % mod;
                }
            }
        }
        return dp[n][k];
    }
}
