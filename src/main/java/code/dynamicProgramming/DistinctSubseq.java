package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/09/26
 */
public class DistinctSubseq {

    /**
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
     * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     * 题目数据保证答案符合 32 位带符号整数范围。
     * <p>
     * 示例 1：
     * 输入：s = "rabbbit", t = "rabbit"
     * 输出：3
     * 解释：
     * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
     * rabbbit
     * rabbbit
     * rabbbit
     * <p>
     * 示例 2：
     * 输入：s = "babgbag", t = "bag"
     * 输出：5
     * 解释：
     * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
     * babgbag
     * babgbag
     * babgbag
     * babgbag
     * babgbag
     * <p>
     * 提示：
     * 0 <= s.length, t.length <= 1000
     * s 和 t 由英文字母组成
     * <p>
     * 力扣：https://leetcode.cn/problems/21dk04/
     * <p>
     * 注：样本对应模型，根据结尾位置的字符划分可能性
     */

    // 暴力递归解法，超过时间限制
    public int numDistinct(String s, String t) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        return process(arr1, arr2, arr1.length, arr2.length);
    }

    public static int process(char[] s, char[] t, int i, int j) {
        if (j == 0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        int res = process(s, t, i - 1, j);
        if (s[i - 1] == t[j - 1]) {
            res += process(s, t, i - 1, j - 1);
        }
        return res;
    }

    // 暴力递归加缓存，直接过
    public int numDistinct2(String s, String t) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int[][] dp = new int[arr1.length + 1][arr2.length + 1];
        for (int i = 0; i <= arr1.length; i++) {
            for (int j = 0; j <= arr2.length; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }
        return process(arr1, arr2, arr1.length, arr2.length, dp);
    }

    public static int process(char[] s, char[] t, int i, int j, int[][] dp) {
        if (dp[i][j] != Integer.MIN_VALUE) {
            return dp[i][j];
        }
        int res;
        if (j == 0) {
            res = 1;
        } else if (i == 0) {
            res = 0;
        } else {
            res = process(s, t, i - 1, j, dp);
            if (s[i - 1] == t[j - 1]) {
                res += process(s, t, i - 1, j - 1, dp);
            }
        }
        dp[i][j] = res;
        return res;
    }

    // 动态规划解法
    public int numDistinct3(String s, String t) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int[][] dp = new int[arr1.length + 1][arr2.length + 1];
        for (int i = 0; i <= arr1.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= arr1.length; i++) {
            for (int j = 1; j <= Math.min(i, arr2.length); j++) {
                dp[i][j] = dp[i - 1][j] + (arr1[i - 1] == arr2[j - 1] ? dp[i - 1][j - 1] : 0);
            }
        }
        return dp[arr1.length][arr2.length];
    }
}
