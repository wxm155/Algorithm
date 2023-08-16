package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/08/15
 */
public class ScrambleString {

    /**
     * 使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
     * 如果字符串的长度为 1 ，算法停止
     * 如果字符串的长度 > 1 ，执行下述步骤：
     * 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。
     * 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x 。
     * 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。
     * 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 示例 1：
     * 输入：s1 = "great", s2 = "rgeat"
     * 输出：true
     * 解释：s1 上可能发生的一种情形是：
     * "great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
     * "gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
     * "gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
     * "g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
     * "r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
     * "r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
     * 算法终止，结果字符串和 s2 相同，都是 "rgeat"
     * 这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
     * <p>
     * 示例 2：
     * 输入：s1 = "abcde", s2 = "caebd"
     * 输出：false
     * <p>
     * 示例 3：
     * 输入：s1 = "a", s2 = "a"
     * 输出：true
     * <p>
     * 提示：
     * s1.length == s2.length
     * 1 <= s1.length <= 30
     * s1 和 s2 由小写英文字母组成
     * <p>
     * 力扣：https://leetcode.cn/problems/scramble-string/
     */

    // 暴力解
    public static boolean isScramble(String s1, String s2) {
        return process(s1.toCharArray(), 0, s1.length() - 1, s2.toCharArray(), 0, s2.length() - 1);
    }

    public static boolean process(char[] s1, int L1, int R1, char[] s2, int L2, int R2) {
        if (L1 == R1) {
            return s1[L1] == s2[L2];
        }
        // s1: 8 .10. 15
        // s2: 8 ...  15
        // leftEnd = 10
        for (int leftEnd = L1; leftEnd < R1; leftEnd++) {
            // s1左边对s2的左边，s1右边对s2的右边
            boolean noExchange = process(s1, L1, leftEnd, s2, L2, L2 + leftEnd - L1)
                    && process(s1, leftEnd + 1, R1, s2, L2 + leftEnd - L1 + 1, R2);
            // s1左边对s2的右边，s1的右边对s2的左边
            boolean exchange = process(s1, L1, leftEnd, s2, R2 - (leftEnd - L1), R2)
                    && process(s1, leftEnd + 1, R1, s2, L2, R2 - (leftEnd - L1) - 1);
            if (noExchange || exchange) {
                return true;
            }
        }
        return false;
    }

    // 记忆化搜索
    public static boolean isScramble2(String s1, String s2) {
        int len = s1.length();
        int[][][][] dp = new int[len][len][len][len];
        return process2(s1.toCharArray(), 0, len - 1, s2.toCharArray(), 0, len - 1, dp);
    }

    public static boolean process2(char[] s1, int L1, int R1, char[] s2, int L2, int R2, int[][][][] dp) {
        if (dp[L1][R1][L2][R2] != 0) {
            return dp[L1][R1][L2][R2] == 1;
        }
        boolean ans = false;
        if (L1 == R1) {
            ans = s1[L1] == s2[L2];
        } else {
            for (int leftEnd = L1; leftEnd < R1; leftEnd++) {
                if (
                        (process2(s1, L1, leftEnd, s2, L2, L2 + leftEnd - L1, dp)
                                && process2(s1, leftEnd + 1, R1, s2, L2 + leftEnd - L1 + 1, R2, dp))
                                ||
                                (process2(s1, L1, leftEnd, s2, R2 - (leftEnd - L1), R2, dp)
                                        && process2(s1, leftEnd + 1, R1, s2, L2, R2 - (leftEnd - L1) - 1, dp))
                ) {
                    ans = true;
                    break;
                }
            }
        }
        dp[L1][R1][L2][R2] = ans ? 1 : -1;
        return ans;
    }


    public static void main(String[] args) {
        String s1 = "great";
        String s2 = "rgeat";
        System.out.println(isScramble2(s1, s2));
    }
}
