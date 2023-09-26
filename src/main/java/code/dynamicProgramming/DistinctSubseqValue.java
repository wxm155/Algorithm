package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/09/26
 */
public class DistinctSubseqValue {

    /**
     * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
     * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
     * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
     * <p>
     * 示例 1：
     * 输入：s = "abc"
     * 输出：7
     * 解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
     * <p>
     * 示例 2：
     * 输入：s = "aba"
     * 输出：6
     * 解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。
     * <p>
     * 示例 3：
     * 输入：s = "aaa"
     * 输出：3
     * 解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
     * <p>
     * 提示：
     * 1 <= s.length <= 2000
     * s 仅由小写英文字母组成
     * <p>
     * 力扣：https://leetcode.cn/problems/distinct-subsequences-ii/
     */

    // 例：abcb
    // 遍历到a字符时，子序列: "",a
    // 遍历到b字符时，子序列: "",a,b,ab
    // 遍历到c字符时，子序列: "",a,b,ab,c,ac,bc,abc
    // 遍历到d字符时，子序列: "",a,b,ab,c,ac,bc,abc + b,ab,bb,abc,cb,acb,bcb,abcb，重复了b,ab
    // 每遍历到一个字符时，新增在前一个字符的子序列每个子序列后面加上当前字符的字符
    // 当遇到重复字符时，重复字符为上次该字符出现新增的字符
    // 子序列数量 = 前一个字符的子序列数量 + 新增子序列数量 - 重复子序列数量
    public static int distinctSubseqII(String s) {
        int mod = 1000000007;
        char[] arr = s.toCharArray();
        // 记录每个字符上一次出现的新增字符数量
        int[] pre = new int[26];
        // 空字符
        int curAns = 1;
        for (char str : arr) {
            int newCount = curAns;
            // 字符数量 = 当前数 + 新增数 - 重复数
            curAns = ((curAns + newCount) % mod - pre[str - 'a'] + mod) % mod;
            pre[str - 'a'] = newCount;
        }
        // 减去空字符
        return (curAns - 1 + mod) % mod;
    }
}
