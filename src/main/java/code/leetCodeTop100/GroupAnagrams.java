package code.leetCodeTop100;

import java.util.*;

/**
 * @author: wxm
 * @created: 2024/01/26
 */
public class GroupAnagrams {

    /**
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
     * <p>
     * 示例 1:
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * <p>
     * 示例 2:
     * 输入: strs = [""]
     * 输出: [[""]]
     * <p>
     * 示例 3:
     * 输入: strs = ["a"]
     * 输出: [["a"]]
     * <p>
     * 提示：
     * 1 <= strs.length <= 10^4
     * 0 <= strs[i].length <= 100
     * strs[i] 仅包含小写字母
     *
     * 力扣：https://leetcode.cn/problems/group-anagrams/?envType=study-plan-v2&envId=top-100-liked
     */

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortStr = new String(chars);
            if (map.containsKey(sortStr)) {
                map.get(sortStr).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sortStr, list);
            }
        }
        return new ArrayList<>(map.values());
    }
}
