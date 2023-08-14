package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/08/11
 */
public class RegularExpressionMatch {

    /**
     * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
     * <p>
     * 示例 1:
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * <p>
     * 示例 2:
     * 输入:
     * s = "aa"
     * p = "a*"
     * 输出: true
     * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * <p>
     * 示例 3:
     * 输入:
     * s = "ab"
     * p = ".*"
     * 输出: true
     * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * <p>
     * 示例 4:
     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * <p>
     * 示例 5:
     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
     * <p>
     * 力扣：https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/
     */

    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        return process(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    // s[si....]能否被p[pi....]匹配出来
    // 以pi+1是否是*进行匹配，因为pi+1是否是*决定pi是否变为空字符还是匹配si
    public static boolean process(char[] s, char[] p, int si, int pi) {
        // pi没有字符了，si剩余字符一定匹配不出来
        if (pi == p.length) {
            return si == s.length;
        }
        // pi+1 不是*
        // 只能s[si] == p[pi]或者p[pi] == '.'搞定s[si] 剩下的继续递归
        if (pi + 1 == p.length || p[pi + 1] != '*') {
            return si != s.length && (s[si] == p[pi] || p[pi] == '.') && process(s, p, si + 1, pi + 1);
        }
        // pi+1 是*
        while (si != s.length && (s[si] == p[pi] || p[pi] == '.')) {
            // s[pi,pi+1]搞定s[si,si+1....]
            // s: a a a a b
            // p: a *
            // p中a*尝试去搞定a,aa,aaa,aaaa,剩下的继续递归
            if (process(s, p, si, pi + 2)) {
                return true;
            }
            si++;
        }
        // s[si]不等于p[pi],x*变为空字符，剩下的继续递归
        return process(s, p, si, pi + 2);
    }

    // 记忆化搜索 + 斜率优化
    public static boolean isMatch2(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        // dp[i][j]：
        // 0:未设置，1:true，-1:false
        int[][] dp = new int[s.length() + 1][p.length() + 1];
        return process2(s.toCharArray(), p.toCharArray(), 0, 0, dp);
    }

    public static boolean process2(char[] s, char[] p, int si, int pi, int[][] dp) {
        if (dp[si][pi] != 0) {
            return dp[si][pi] == 1;
        }
        boolean ans = false;
        if (pi == p.length) {
            ans = si == s.length;
        } else {
            if (pi + 1 == p.length || p[pi + 1] != '*') {
                ans = si != s.length && (s[si] == p[pi] || p[pi] == '.') && process2(s, p, si + 1, pi + 1, dp);
            } else {
                if (si == s.length || (s[si] != p[pi] && p[pi] != '.')) {
                    ans = process2(s, p, si, pi + 2, dp);
                } else {
                    // f(5,6) = f(5,8) || f(6,8) || f(7,8)
                    // f(6,6) = f(6,8) || f(7,8)
                    ans = process2(s, p, si, pi + 2, dp) || process2(s, p, si + 1, pi, dp);
                }
            }
        }
        dp[si][pi] = ans ? 1 : -1;
        return ans;
    }

    public static void main(String[] args) {
        String s = "aa";
        String e = "a*";
        System.out.println(isMatch(s, e));
        System.out.println(isMatch2(s, e));
    }
}
