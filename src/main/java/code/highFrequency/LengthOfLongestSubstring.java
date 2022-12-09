package code.highFrequency;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wxm
 * @created 2022/11/27
 */
public class LengthOfLongestSubstring {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。
     *
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子字符串是 "abc"，所以其长度为 3。
     *
     * 示例 2:
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子字符串是 "b"，所以其长度为 1。
     *
     * 示例 3:
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * 示例 4:
     * 输入: s = ""
     * 输出: 0
     *
     * 提示：
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     *
     * 力扣：https://leetcode.cn/problems/wtcaE1/
     *
     * 总结：子串和子数组问题，看着以当前位置为结尾，向左能达到的位置
     */

    /**
     * 动态规划最优解
     * @param s 输入字符串
     * @return 返回最长无重复字符长度
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        // ASCII字符的长度为255
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        int pre = 1;
        int ans = 1;
        map[chars[0]] = 0;
        for (int i = 1; i < len; i++) {
            // chars[i] 上一次出现的位置有两种情况
            // 1、不包含在前一个字符的的长度中
            //    a b c d b a
            //    0 1 2 3 4 i  => pre + 1
            // 2、包含在前一个位置
            //    a b c a b a
            //    0 1 2 3 4 i  => i - map[chars[i]]
            pre = Math.min(i - map[chars[i]], pre + 1);
            ans = Math.max(ans, pre);
            // 记录当前字符出现的位置
            map[chars[i]] = i;
        }
        return ans;
    }

    /**
     * 滑动窗口 遍历有边界
     *
     * @param s 输入字符串
     * @return 返回最长无重复字符长度
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int len = s.length();
        Set<Character> set = new HashSet<>();
        int l = 0;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            // pwwkew
            while (set.contains(c)) {
                set.remove(s.charAt(l));
                l++;
            }
            set.add(c);
            ans = Math.max(ans, i - l + 1);
        }
        return ans;
    }

}
