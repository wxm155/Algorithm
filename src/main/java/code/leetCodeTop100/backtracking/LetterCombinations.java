package code.leetCodeTop100.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2024/04/10
 */
public class LetterCombinations {
    
    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 示例 1： 输入：digits = "23" 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * <p>
     * 示例 2： 输入：digits = "" 输出：[]
     * <p>
     * 示例 3： 输入：digits = "2" 输出：["a","b","c"]
     * <p>
     * 提示： 0 <= digits.length <= 4， digits[i] 是范围 ['2', '9'] 的一个数字。
     * <p>
     * 力扣：https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/?envType=study-plan-v2&envId=top-100-liked
     */
    
    public List<String> letterCombinations(String digits) {
        
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        
        String temp = "";
        process(digits.toCharArray(), 0, digits.length(), res, temp, map);
        return res;
    }
    
    public void process(char[] str, int index, int len, List<String> res, String temp, Map<Character, String> map) {
        
        if (index == len) {
            res.add(temp);
            return;
        }
        
        String strNum = map.get(str[index]);
        char[] chars = strNum.toCharArray();
        
        for (int i = 0; i < chars.length; i++) {
            
            char aChar = chars[i];
            String newStr = temp + aChar;
            
            process(str, index + 1, len, res, newStr, map);
        }
    }
    
}
