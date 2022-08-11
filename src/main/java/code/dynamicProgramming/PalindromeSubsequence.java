package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/08/11
 */
public class PalindromeSubsequence {

    /**
     * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     *
     * 示例 1：
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     *
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出：2
     * 解释：一个可能的最长回文子序列为 "bb" 。
     *
     * 力扣：https://leetcode.cn/problems/longest-palindromic-subsequence/
     */

    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return process1(s.toCharArray(), 0, s.length() - 1);
    }

    public static int process1(char[] arr, int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l == r - 1) {
            return arr[l] == arr[r - 1] ? 2 : 1;
        }
        int p1 = process1(arr, l + 1, r);
        int p2 = process1(arr, l, r - 1);
        int p3 = process1(arr, l + 1, r - 1);
        int p4 = arr[l] != arr[r] ? 0 : (2 + process1(arr, l + 1, r - 1));
        return Math.max(Math.max(p1,p2), Math.max(p3, p4));
    }

    // 动态规划解法
    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int len = arr.length;
        int[][] dp = new int[len][len];
        dp[len - 1][len - 1] = 1;
        // 填充对角线和对角线的上移一条斜线
        for (int l = 0; l < len - 1; l++) {
            dp[l][l] = 1;
            dp[l][l + 1] = arr[l] == arr[l + 1] ? 2 : 1;
        }
        // 填充剩余位置
        for (int l = len - 3; l >= 0; l--) {
            for (int r = l + 2; r < len; r++) {
                dp[l][r] = Math.max(dp[l + 1][r], dp[l][r - 1]);
                if (arr[l] == arr[r]) {
                    dp[l][r] = Math.max(dp[l][r], 2 + dp[l + 1][r - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
