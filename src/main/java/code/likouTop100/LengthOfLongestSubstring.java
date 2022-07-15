package code.likouTop100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2022/07/15
 */
public class LengthOfLongestSubstring {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
     *
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 示例 2:
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     * 示例 3:
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * 力扣：https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     */


    /**
     * 滑动窗口解法
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s == ""){
            return 0;
        }
        int res = 0;
        int left = 0; // abcabcbb
        char[] chars = s.toCharArray();
        Map<Character,Integer> tempMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (tempMap.containsKey(chars[i])){
                left = Math.max(left,tempMap.get(chars[i]) + 1);
            }
            res = Math.max(res,i - left + 1);
            tempMap.put(chars[i],i);
        }
        return res;
    }

}
