package code.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxm
 * @created: 2023/08/01
 */
public class PalindromePartitioningII {

    /**
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
     * 返回符合要求的 最少分割次数 。
     * <p>
     * 示例 1：
     * 输入：s = "aab"
     * 输出：1
     * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
     * <p>
     * 示例 2：
     * 输入：s = "a"
     * 输出：0
     * <p>
     * 示例 3：
     * 输入：s = "ab"
     * 输出：1
     * <p>
     * 提示：
     * 1 <= s.length <= 2000
     * s 仅由小写英文字母组成
     * <p>
     * 力扣：https://leetcode.cn/problems/palindrome-partitioning-ii/
     */

    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int N = s.length();
        char[] str = s.toCharArray();
        boolean[][] checkMap = createCheckMap(str, N);
        // dp[i]表示str[i...N-1]范围上最少有多少个回文串
        int[] dp = new int[N];
        // 从右往左填
        for (int i = N - 1; i >= 0; i--) {
            if (checkMap[i][N - 1]) {
                dp[i] = 1;
            } else {
                int next = Integer.MAX_VALUE;
                // 依次求出最少的回文串个数
                for (int j = i; j < N; j++) {
                    // 如果str[i...j]是回文串，则str[i...N-1]的回文串个数为dp[j + 1] + 1
                    if (checkMap[i][j]) {
                        next = Math.min(next, dp[j + 1]);
                    }
                }
                dp[i] = next + 1;
            }
        }
        // 切的次数
        return dp[0] - 1;
        // 从左往右填
//        for (int i = 0; i < N; i++) {
//            if (checkMap[0][i]){
//                dp[i] = 1;
//            }else {
//                int min = Integer.MAX_VALUE;
//                for (int j = 0; j <= i; j++) {
//                    if (checkMap[j][i]){
//                        min = Math.min(min,dp[j - 1]);
//                    }
//                }
//                dp[i] = min + 1;
//            }
//        }
//        return dp[N - 1] - 1;
    }

    public static boolean[][] createCheckMap(char[] str, int N) {
        // dp[i][j]表示str[i...j]是否为回文串
        boolean[][] dp = new boolean[N][N];
        for (int i = 0; i < N - 1; i++) {
            // 当只有一个字符时，必为回文串
            dp[i][i] = true;
            // 当有两个字符时，相等则为回文串
            dp[i][i + 1] = str[i] == str[i + 1];
        }
        // 对角线最右下角位置
        dp[N - 1][N - 1] = true;
        // 从右下角开始，从左往右填
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                // 普遍位置当首位字符相等，并且中间部分也为回文串则为回文串
                dp[i][j] = str[i] == str[j] && dp[i + 1][j - 1];
            }
        }
        return dp;
    }

    /**
     * 在问题1的基础上返回问题一的其中一种划分结果
     */
    public static List<String> minCutOneWay(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            ans.add(s);
        } else {
            int N = s.length();
            char[] str = s.toCharArray();
            boolean[][] checkMap = createCheckMap(str, N);
            // dp[i]表示str[i...N-1]范围上最少有多少个回文串
            int[] dp = new int[N + 1];
            // 从右往左填
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    int next = Integer.MAX_VALUE;
                    // 依次求出最少的回文串个数
                    for (int j = i; j < N; j++) {
                        // 如果str[i...j]是回文串，则str[i...N-1]的回文串个数为dp[j + 1] + 1
                        if (checkMap[i][j]) {
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    dp[i] = next + 1;
                }
            }
            for (int i = 0, j = 1; j <= N; j++) {
                if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                    ans.add(s.substring(i, j));
                    i = j;
                }
            }
        }
        return ans;
    }

    /**
     * 在问题一的基础上返回所有划分结果
     */
    public static List<List<String>> minCutAllWays(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            List<String> cur = new ArrayList<>();
            cur.add(s);
            ans.add(cur);
        } else {
            int N = s.length();
            char[] str = s.toCharArray();
            boolean[][] checkMap = createCheckMap(str, N);
            // dp[i]表示str[i...N-1]范围上最少有多少个回文串
            int[] dp = new int[N + 1];
            // 从右往左填
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    int next = Integer.MAX_VALUE;
                    // 依次求出最少的回文串个数
                    for (int j = i; j < N; j++) {
                        // 如果str[i...j]是回文串，则str[i...N-1]的回文串个数为dp[j + 1] + 1
                        if (checkMap[i][j]) {
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    dp[i] = next + 1;
                }
            }
            process(s, 0, 1, checkMap, dp, new ArrayList<>(), ans);
        }
        return ans;
    }

    // str[i...j]上划分的回文串放在ans中
    // path => 记录每条分支分割的结果
    public static void process(String str, int i, int j, boolean[][] checkMap, int[] dp, List<String> path, List<List<String>> ans) {
        if (j == str.length()) {
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                path.add(str.substring(i, j));
                ans.add(new ArrayList<>(path));
                // 深度优先遍历恢复现场
                path.remove(path.size() - 1);
            }
        } else {
            // 当前满足分割，继续深度优先遍历
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                path.add(str.substring(i, j));
                process(str, j, j + 1, checkMap, dp, path, ans);
                // 深度优先遍历恢复现场
                path.remove(path.size() - 1);
            }
            // 不满足分割，继续深度优先遍历
            process(str, i, j + 1, checkMap, dp, path, ans);
        }
    }

    public static void main(String[] args) {
        String s = null;
        List<String> ans2 = null;
        List<List<String>> ans3 = null;

        System.out.println("本题第二问，返回其中一种结果测试开始");
        s = "abacbc";
        ans2 = minCutOneWay(s);
        for (String str : ans2) {
            System.out.print(str + " ");
        }
        System.out.println();

        s = "aabccbac";
        ans2 = minCutOneWay(s);
        for (String str : ans2) {
            System.out.print(str + " ");
        }
        System.out.println();

        s = "aabaa";
        ans2 = minCutOneWay(s);
        for (String str : ans2) {
            System.out.print(str + " ");
        }
        System.out.println();
        System.out.println("本题第二问，返回其中一种结果测试结束");
        System.out.println();
        System.out.println("本题第三问，返回所有可能结果测试开始");
        s = "cbbbcbc";
        ans3 = minCutAllWays(s);
        for (List<String> way : ans3) {
            for (String str : way) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println();

        s = "aaaaaa";
        ans3 = minCutAllWays(s);
        for (List<String> way : ans3) {
            for (String str : way) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println();

        s = "fcfffcffcc";
        ans3 = minCutAllWays(s);
        for (List<String> way : ans3) {
            for (String str : way) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("本题第三问，返回所有可能结果测试结束");
    }
}
