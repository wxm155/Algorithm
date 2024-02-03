package code.leetCodeTop100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: wxm
 * @created: 2024/01/30
 */
public class FindAnagrams {

    /**
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * <p>
     * 示例 1:
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     * <p>
     * 示例 2:
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     * <p>
     * 提示:
     * 1 <= s.length, p.length <= 3 * 10^4
     * s 和 p 仅包含小写字母
     * <p>
     * 力扣：https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/?envType=study-plan-v2&envId=top-100-liked
     */

    public static List<Integer> findAnagrams(String s, String p) {

        List<Integer> res = new ArrayList<>();
        char[] arr2 = p.toCharArray();
        Arrays.sort(arr2);
        String sort = new String(arr2);
        int len = p.length();

        for (int i = 0; i <= s.length() - len; i++) {
            String sub = s.substring(i, i + len);
            char[] subArr = sub.toCharArray();
            Arrays.sort(subArr);
            String subStr = new String(subArr);
            if (subStr.equals(sort)) {
                res.add(i);
            }
        }
        return res;
    }

    // 利用词频作为滑动窗口
    public static List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();
        if (sLen < pLen) {
            return res;
        }
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        char[] pChars = p.toCharArray();
        char[] sChars = s.toCharArray();
        for (int i = 0; i < pLen; i++) {
            sCount[sChars[i] - 'a']++;
            pCount[pChars[i] - 'a']++;
        }
        if (Arrays.equals(pCount, sCount)) {
            res.add(0);
        }
        for (int i = 0; i < sLen - pLen; i++) {
            sCount[sChars[i] - 'a']--;
            sCount[sChars[i + pLen] - 'a']++;

            if (Arrays.equals(pCount, sCount)) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abab";
        String p = "ab";
        System.out.println(findAnagrams(s, p));

    }
}
