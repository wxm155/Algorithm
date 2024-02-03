package code.leetCodeTop100;

import java.util.*;

/**
 * @author wxm
 * @created 2024/2/1
 */
public class LetterCombinations {

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 示例 1：
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * <p>
     * 示例 2：
     * 输入：digits = ""
     * 输出：[]
     * <p>
     * 示例 3：
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     * <p>
     * 提示：
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     * <p>
     * 力扣：https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/?envType=study-plan-v2&envId=top-100-liked
     */

    public List<String> letterCombinations(String digits) {
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        List<String> res = new ArrayList<>();
        if (digits.equals("")){
            return res;
        }
        process(digits, 0, new StringBuilder(), map, res);
        return res;
    }

    private void process(String digits, int index, StringBuilder builder, Map<Character, String> map, List<String> res) {
        if (digits.length() == index) {
            res.add(builder.toString());
        } else {
            String str = map.get(digits.charAt(index));
            for (int i = 0; i < str.length(); i++) {
                char charAt = str.charAt(i);
                builder.append(charAt);
                process(digits, index + 1, builder, map, res);
                builder.deleteCharAt(index);
            }
        }
    }
}
