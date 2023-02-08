package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/02/08
 */
public class InterleavingString {

    /**
     * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
     * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
     * s = s1 + s2 + ... + sn
     * t = t1 + t2 + ... + tm
     * |n - m| <= 1
     * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
     * 注意：a + b 意味着字符串 a 和 b 连接。
     *
     * 示例 1：
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * 输出：true
     * 示例 2：
     *
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * 输出：false
     * 示例 3：
     *
     * 输入：s1 = "", s2 = "", s3 = ""
     * 输出：true
     *
     * 提示：
     * 0 <= s1.length, s2.length <= 100
     * 0 <= s3.length <= 200
     * s1、s2、和 s3 都由小写英文字母组成
     *
     * 进阶：您能否仅使用 O(s2.length) 额外的内存空间来解决它?
     *
     * 力扣：https://leetcode.cn/problems/interleaving-string/description/
     */

    /**
     * @param s1 字符串1
     * @param s2 字符串2
     * @param s3 字符串3
     * @return result
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
        if (l1 + l2 != l3) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        // dp[i][j]表示s1的前i个字符和s2的前j个字符是否能构成s3的前i + j个字符
        boolean[][] dp = new boolean[l1 + 1][l2 + 1];
        dp[0][0] = true;
        // 第一行 str1[i - 1] 对于第i个字符
        for (int i = 1; i <= l1; i++) {
            if (str1[i - 1] != str3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        // 第一列
        for (int i = 1; i <= l2; i++) {
            if (str2[i - 1] != str3[i - 1]) {
                break;
            }
            dp[0][i] = true;
        }
        // 其它位置
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if ((dp[i][j - 1] && str2[j - 1] == str3[i + j - 1]) || dp[i - 1][j] && str1[i - 1] == str3[i + j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[l1][l2];
    }
}
