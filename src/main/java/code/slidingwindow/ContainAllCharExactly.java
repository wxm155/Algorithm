package code.slidingwindow;

/**
 * @author: wxm
 * @created: 2023/08/04
 */
public class ContainAllCharExactly {

    /**
     * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：s1 = "ab" s2 = "eidbaooo"
     * 输出：true
     * 解释：s2 包含 s1 的排列之一 ("ba").
     * 示例 2：
     * <p>
     * 输入：s1= "ab" s2 = "eidboaoo"
     * 输出：false
     * <p>
     * 提示：
     * 1 <= s1.length, s2.length <= 10^4
     * s1 和 s2 仅包含小写字母
     * <p>
     * 力扣：https://leetcode.cn/problems/MPnaiL/
     */

    // 滑动窗口+map表
    // all的含义：在str1的字符数中，窗口往右滑动中，右边窗口进的字符在count中大于0的时候才减
    //          左边窗口出的字符在count中大于等于0时才加
    // 核心思想：当all为0时，即str1中的所有字符和数量一定在str2对应的窗口中
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }
        int len1 = s1.length();
        int len2 = s2.length();
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int all = len1;
        // 所有字符的计数表
        int[] count = new int[256];
        for (int i = 0; i < len1; i++) {
            count[str1[i]]++;
        }
        // 窗口初步形成，形成str1长度大小的窗口，
        // 每遇到一个str1中一个字符并减去一个，减去后数量大于0，则str1中的所有字符all--
        // 当all == 0时，一定存在str1的一个变形词是str2的一个子串
        for (int i = 0; i < len1; i++) {
            if (count[str2[i]]-- > 0) {
                all--;
            }
        }
        // 窗口左边出，右边进
        // 只有当count[str1[i]]的数量大于0时，右边进的才all--
        // 当count[str1[i]]的数量大于等于0时，左边出的才all++
        for (int i = len1; i < len2; i++) {
            if (all == 0) {
                return true;
            }
            // 右边窗口进
            if (count[str2[i]]-- > 0) {
                all--;
            }
            // 左边窗口出
            if (count[str2[i - len1]]++ >= 0) {
                all++;
            }
        }
        return all == 0;
    }
}
