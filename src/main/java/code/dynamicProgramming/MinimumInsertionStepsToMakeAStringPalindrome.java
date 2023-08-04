package code.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxm
 * @created: 2023/08/02
 */
public class MinimumInsertionStepsToMakeAStringPalindrome {

    /**
     * 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
     * 请你返回让 s 成为回文串的 最少操作次数 。
     * 「回文串」是正读和反读都相同的字符串。
     * <p>
     * 示例 1：
     * 输入：s = "zzazz"
     * 输出：0
     * 解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
     * <p>
     * 示例 2：
     * 输入：s = "mbadm"
     * 输出：2
     * 解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
     * <p>
     * 示例 3：
     * 输入：s = "leetcode"
     * 输出：5
     * 解释：插入 5 个字符后字符串变为 "leetcodocteel" 。
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 500
     * s 中所有字符都是小写字母。
     * <p>
     * 力扣：https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome/
     * <p>
     * 注：利用动态规划表回溯结果，同一类型题 {@link PalindromePartitioningII}
     */

    public int minInsertions(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int len = s.length();
        // dp[i][j]表示str[i...j]是回文串至少需要添加的字符数
        int[][] dp = new int[len][len];
        // dp[i][i]不需要添加字符就是回文串
        // dp[i][i + 1] => 当str[i] != str[i + 1]时需要添加一个字符成为回文串
        for (int i = 0; i < len - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }
        // abac
        // 当i=0,j=3时，有三种情况
        // 1、将最后一个c添加一个在开头位置 => dp[i][j - 1] + 1
        // 2、将第一个位置的a添加一个在结束位置 => dp[i + 1][j] + 1
        // 3、当str[i] == str[j]时，只需i+1...j-1是回文串需要添加的字符数，不需要额外添加字符
        // 三种情况取最小值
        // dp[i][j]依赖左边和下边的格子 => dp[i][j - 1]、dp[i + 1][j]
        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }

    // 返回问题一的其中一种添加结果
    public static String minInsertionsOneWay(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        char[] str = s.toCharArray();
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }
        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        return getOneWay(str, dp);
    }

    public static String getOneWay(char[] str, int[][] dp) {
        char[] ans = new char[str.length + dp[0][str.length - 1]];
        int L = 0, R = str.length - 1;
        int ansL = 0, ansR = ans.length - 1;
        while (L < R) {
            // 情况一：是以结尾字符添加一个在开头
            if (dp[L][R] == dp[L][R - 1] + 1) {
                ans[ansL++] = str[R];
                ans[ansR--] = str[R--];
                // 情况二：是以开头字符添加一个在结尾
            } else if (dp[L][R] == dp[L + 1][R] + 1) {
                ans[ansL++] = str[L];
                ans[ansR--] = str[L++];
                // 情况三：开头字符和结尾字符相同
            } else {
                ans[ansL++] = str[L++];
                ans[ansR--] = str[R--];
            }
        }
        if (L == R) {
            ans[ansL] = str[L];
        }
        return String.valueOf(ans);
    }

    public static List<String> minInsertionsAllWays(String s) {
        if (s == null || s.length() < 2) {
            List<String> ans = new ArrayList<>();
            ans.add(s);
            return ans;
        }
        char[] str = s.toCharArray();
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }
        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        char[] path = new char[len + dp[0][len - 1]];
        List<String> ans = new ArrayList<>();
        getAllWays(str, 0, len - 1, path, 0, path.length - 1, dp, ans);
        return ans;
    }

    public static void getAllWays(char[] str, int L, int R, char[] path, int pl, int pr, int[][] dp, List<String> ans) {
        if (L >= R) {
            if (L == R) {
                path[pl] = str[L];
            }
            ans.add(String.valueOf(path));
        } else {
            // 深度优先遍历不需要恢复现场，因为每次都会覆盖当前值
            if (dp[L][R] == dp[L][R - 1] + 1) {
                path[pl] = str[R];
                path[pr] = str[R];
                getAllWays(str, L, R - 1, path, pl + 1, pr - 1, dp, ans);
            }
            if (dp[L][R] == dp[L + 1][R] + 1) {
                path[pl] = str[L];
                path[pr] = str[L];
                getAllWays(str, L + 1, R, path, pl + 1, pr - 1, dp, ans);
            }
            if (str[L] == str[R] && (L == R - 1 || dp[L + 1][R - 1] == dp[L][R])) {
                path[pl] = str[L];
                path[pr] = str[R];
                getAllWays(str, L + 1, R - 1, path, pl + 1, pr - 1, dp, ans);
            }
        }
    }

    public static void main(String[] args) {
        String s = null;
        String ans2 = null;
        List<String> ans3 = null;

        System.out.println("本题第二问，返回其中一种结果测试开始");
        s = "mbadm";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);

        s = "leetcode";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);

        s = "aabaa";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);
        System.out.println("本题第二问，返回其中一种结果测试结束");

        System.out.println();

        System.out.println("本题第三问，返回所有可能的结果测试开始");
        s = "mbadm";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();

        s = "leetcode";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();

        s = "aabaa";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();
        System.out.println("本题第三问，返回所有可能的结果测试结束");
    }

}
