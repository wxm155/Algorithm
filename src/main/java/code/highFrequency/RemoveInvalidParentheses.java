package code.highFrequency;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wxm
 * @created: 2023/07/18
 */
public class RemoveInvalidParentheses {

    /**
     * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
     * 返回所有可能的结果。答案可以按 任意顺序 返回。
     * <p>
     * 示例 1：
     * 输入：s = "()())()"
     * 输出：["(())()","()()()"]
     * <p>
     * 示例 2：
     * 输入：s = "(a)())()"
     * 输出：["(a())()","(a)()()"]
     * <p>
     * 示例 3：
     * 输入：s = ")("
     * 输出：[""]
     * <p>
     * 提示：
     * 1 <= s.length <= 25
     * s 由小写英文字母以及括号 '(' 和 ')' 组成
     * s 中至多含 20 个括号
     * <p>
     * 力扣：https://leetcode.cn/problems/remove-invalid-parentheses/
     */

    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        process(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    /**
     * 先检查左括号，再检查右括号
     *
     * @param s           字符串
     * @param ans         结果
     * @param checkIndex  检查位置
     * @param deleteIndex 删除位置
     * @param par         括号字符
     */
    public static void process(String s, List<String> ans, int checkIndex, int deleteIndex, char[] par) {
        for (int count = 0, i = checkIndex; i < s.length(); i++) {
            // 左/右括号时count++
            if (s.charAt(i) == par[0]) {
                count++;
            }
            // 左/右括号时count--
            if (s.charAt(i) == par[1]) {
                count--;
            }
            // count < 0出现了需要删除的左/右括号
            if (count < 0) {
                for (int j = deleteIndex; j <= i; ++j) {
                    // 当前括号为左/右括号，并且前一个不是左/右括号(连续的右括号删除为同一个字符串)
                    if (s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])) {
                        // 删除左/右括号
                        String newStr = s.substring(0, j) + s.substring(j + 1);
                        process(newStr, ans, i, j, par);
                    }
                }
                // 出现需要删除的左/右括号，子过程已经将后续的结果处理好了
                return;
            }
        }
        // 将字符串反转，反转后检查完会再反转为正常的字符串
        String reverse = new StringBuilder(s).reverse().toString();
        // 如果之前删除的是右括号，反转后继续删除左括号
        if (par[0] == '(') {
            process(reverse, ans, 0, 0, new char[]{')', '('});
        } else {
            // 左右括号都检查了，添加结果
            ans.add(reverse);
        }
    }
}
