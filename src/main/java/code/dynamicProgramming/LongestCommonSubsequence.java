package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/08/10
 */
public class LongestCommonSubsequence {

    /**
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
     * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
     *
     * 示例 1：
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
     *
     * 示例 2：
     * 输入：text1 = "abc", text2 = "abc"
     * 输出：3
     * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
     *
     * 示例 3：
     * 输入：text1 = "abc", text2 = "def"
     * 输出：0
     * 解释：两个字符串没有公共子序列，返回 0 。
     *
     * 力扣：https://leetcode.cn/problems/longest-common-subsequence/
     */

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0){
            return 0;
        }
        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();
        return process1(arr1,arr2,arr1.length - 1,arr2.length - 1);
    }

    // str1[0...i]和str2[0...j]，这个范围上最长公共子序列长度是多少？
    // 可能性分类:
    // a) 最长公共子序列，一定不以str1[i]字符结尾、也一定不以str2[j]字符结尾
    // b) 最长公共子序列，可能以str1[i]字符结尾、但是一定不以str2[j]字符结尾
    // c) 最长公共子序列，一定不以str1[i]字符结尾、但是可能以str2[j]字符结尾
    // d) 最长公共子序列，必须以str1[i]字符结尾、也必须以str2[j]字符结尾
    // 注意：a)、b)、c)、d)并不是完全互斥的，他们可能会有重叠的情况
    // 但是可以肯定，答案不会超过这四种可能性的范围
    // 那么我们分别来看一下，这几种可能性怎么调用后续的递归。
    // a) 最长公共子序列，一定不以str1[i]字符结尾、也一定不以str2[j]字符结尾
    //    如果是这种情况，那么有没有str1[i]和str2[j]就根本不重要了，因为这两个字符一定没用啊
    //    所以砍掉这两个字符，最长公共子序列 = str1[0...i-1]与str2[0...j-1]的最长公共子序列长度(后续递归)
    // b) 最长公共子序列，可能以str1[i]字符结尾、但是一定不以str2[j]字符结尾
    //    如果是这种情况，那么我们可以确定str2[j]一定没有用，要砍掉；但是str1[i]可能有用，所以要保留
    //    所以，最长公共子序列 = str1[0...i]与str2[0...j-1]的最长公共子序列长度(后续递归)
    // c) 最长公共子序列，一定不以str1[i]字符结尾、但是可能以str2[j]字符结尾
    //    跟上面分析过程类似，最长公共子序列 = str1[0...i-1]与str2[0...j]的最长公共子序列长度(后续递归)
    // d) 最长公共子序列，必须以str1[i]字符结尾、也必须以str2[j]字符结尾
    //    同时可以看到，可能性d)存在的条件，一定是在str1[i] == str2[j]的情况下，才成立的
    //    所以，最长公共子序列总长度 = str1[0...i-1]与str2[0...j-1]的最长公共子序列长度(后续递归) + 1(共同的结尾)
    public static int process1(char[] arr1, char[] arr2, int i, int j) {
        if (i == 0 && j == 0) {
            return arr1[i] == arr2[j] ? 1 : 0;
        } else if (i == 0) {
            if (arr1[i] == arr2[j]) {
                return 1;
            } else {
                return process1(arr1, arr2, i, j - 1);
            }
        } else if (j == 0) {
            if (arr1[i] == arr2[j]) {
                return 1;
            } else {
                return process1(arr1, arr2, i - 1, j);
            }
        } else {
            int p1 = process1(arr1, arr2, i - 1, j);
            int p2 = process1(arr1, arr2, i, j - 1);
            int p3 = arr1[i] == arr2[j] ? (process1(arr1, arr2, i - 1, j - 1) + 1) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    // 动态规划解法
    public int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();
        int[][] dp = new int[arr1.length][arr2.length];
        // 填充dp[0][0]
        dp[0][0] = arr1[0] == arr2[0] ? 1 : 0;
        // 填充dp[0][0....arr2.length]
        for (int j = 1; j < arr2.length; j++) {
            dp[0][j] = arr1[0] == arr2[j] ? 1 : dp[0][j - 1];
        }
        // 填充dp[0....arr1.length][0]
        for (int i = 1; i < arr1.length; i++) {
            dp[i][0] = arr1[i] == arr2[0] ? 1 : dp[i - 1][0];
        }
        // 填充dp[1....arr1.length][1....arr2.length]
        for (int i = 1; i < arr1.length; i++) {
            for (int j = 1; j < arr2.length; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = arr1[i] == arr2[j] ? (dp[i - 1][j - 1] + 1) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[arr1.length - 1][arr2.length - 1];
    }
}
