package code.dynamicProgramming;

/**
 * @author wxm
 * @created 2022/9/29
 */
public class StrangePrinter {

    /**
     * 有台奇怪的打印机有以下两个特殊要求：
     * 打印机每次只能打印由 同一个字符 组成的序列。
     * 每次可以在从起始到结束的任意位置打印新字符，并且会覆盖掉原来已有的字符。
     * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
     *
     * 示例 1：
     * 输入：s = "aaabbb"
     * 输出：2
     * 解释：首先打印 "aaa" 然后打印 "bbb"。
     *
     * 示例 2：
     * 输入：s = "aba"
     * 输出：2
     * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
     *
     * 提示：
     * 1 <= s.length <= 100
     * s 由小写英文字母组成
     *
     * 力扣：https://leetcode.cn/problems/strange-printer/
     */

    // 暴力尝试
    public int strangePrinter(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        return process(arr, 0, arr.length - 1);
    }

    // 以arr[L]字符为开头，在arr[L...R]上刷出字符串需要多少次数
    public static int process(char[] arr, int L, int R) {
        if (L == R) {
            return 1;
        }
        // 最多需要的次数
        int ans = R - L + 1;
        // arr[L] == arr[i] ? 1 : 0
        // 左边的首字符和右边的首字符相同只需一遍即可刷完，需减去多余次数
        for (int i = L + 1; i <= R; i++) {
            ans = Math.min(ans, process(arr, L, i - 1) + process(arr, i, R) - (arr[L] == arr[i] ? 1 : 0));
        }
        return ans;
    }

    // 暴力尝试 + 缓存
    public int strangePrinter1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int len = arr.length;
        int[][] dp = new int[len][len];
        return process1(arr, 0, len - 1, dp);
    }

    public static int process1(char[] arr, int L, int R, int[][] dp) {
        if (L == R) {
            return 1;
        }
        if (dp[L][R] != 0) {
            return dp[L][R];
        }
        // 最多需要的次数
        int ans = R - L + 1;
        // arr[L] == arr[i] ? 1 : 0
        // 左边的首字符和右边的首字符相同只需一遍即可刷完，需减去多余次数
        for (int i = L + 1; i <= R; i++) {
            ans = Math.min(ans, process1(arr, L, i - 1, dp) + process1(arr, i, R, dp) - (arr[L] == arr[i] ? 1 : 0));
        }
        dp[L][R] = ans;
        return ans;
    }

    // 动态规划
    public int strangePrinter2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] arr = s.toCharArray();
        int len = arr.length;
        int[][] dp = new int[len][len];
        // 对角线
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int L = len - 2; L >= 0; L--) {
            for (int R = L + 1; R < len; R++) {
                // 最多需要的次数
                int ans = R - L + 1;
                // arr[L] == arr[i] ? 1 : 0
                // 左边的首字符和右边的首字符相同只需一遍即可刷完，需减去多余次数
                for (int i = L + 1; i <= R; i++) {
                    ans = Math.min(ans, dp[L][i - 1] + dp[i][R] - (arr[L] == arr[i] ? 1 : 0));
                }
                dp[L][R] = ans;
            }
        }
        return dp[0][len - 1];
    }
}
