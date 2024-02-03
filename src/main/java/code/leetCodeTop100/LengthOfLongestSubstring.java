package code.leetCodeTop100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2024/01/29
 */
public class LengthOfLongestSubstring {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 示例 2:
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * <p>
     * 示例 3:
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * <p>
     * 提示：
     * 0 <= s.length <= 5 * 10^4
     * s 由英文字母、数字、符号和空格组成
     * <p>
     * 力扣：https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/?envType=study-plan-v2&envId=top-100-liked
     */

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s == "") {
            return -1;
        }
        char[] arr = s.toCharArray();
        int left = 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                // +1表示left为离当前字符最近的字符的右边的第一个字符位置
                left = Math.max(left, map.get(arr[i]) + 1);
            }
            res = Math.max(res, i - left + 1);
            map.put(arr[i], i);
        }
        return res;
    }
}
