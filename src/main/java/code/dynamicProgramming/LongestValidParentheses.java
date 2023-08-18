package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/08/17
 */
public class LongestValidParentheses {

    /**
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     * <p>
     * 示例 1：
     * 输入：s = "(()"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     * <p>
     * 示例 2：
     * 输入：s = ")()())"
     * 输出：4
     * 解释：最长有效括号子串是 "()()"
     * <p>
     * 示例 3：
     * 输入：s = ""
     * 输出：0
     * <p>
     * 提示：
     * 0 <= s.length <= 3 * 10^4
     * s[i] 为 '(' 或 ')'
     * <p>
     * 力扣：https://leetcode.cn/problems/longest-valid-parentheses/
     */

    // 子串、子数组通常都是以当前位置为结尾向左能扩出的距离
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int pre = 0, ans = 0;
        // dp[i] 以str[i]为结尾能向左扩出最大的长度
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (str[i] == ')') {
                // 最远有效的左括号的位置
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
