package code.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2022/03/09
 */
public class LengthOfLongestSubstring {

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * <p>
     * 力扣：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     */
    // "pwwkew"
    public static int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();
        int max = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)){
                start = Math.max(map.get(ch)+1,start);
            }
            max = Math.max(max,i - start + 1);
            map.put(ch,i);
        }
        return max;
    }

    public static void main(String[] args) {
        int pwwkew = lengthOfLongestSubstring("pwwkew");
        System.out.println(pwwkew);
    }
}
