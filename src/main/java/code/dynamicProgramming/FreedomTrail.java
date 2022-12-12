package code.dynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2022/12/12
 */
public class FreedomTrail {

    /**
     * 电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。
     * 给定一个字符串 ring ，表示刻在外环上的编码；给定另一个字符串 key ，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
     * 最初，ring 的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
     * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
     * 您可以将 ring 顺时针或逆时针旋转 一个位置 ，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
     * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
     *  
     * 示例 1：
     * 输入: ring = "godding", key = "gd"
     * 输出: 4
     * 解释:
     *  对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
     *  对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
     *  当然, 我们还需要1步进行拼写。
     *  因此最终的输出是 4。
     *
     * 示例 2:
     * 输入: ring = "godding", key = "godding"
     * 输出: 13
     *  
     * 提示：
     * 1 <= ring.length, key.length <= 100
     * ring和key只包含小写英文字母
     * 保证字符串key一定可以由字符串ring旋转拼出
     *
     * 力扣：https://leetcode.cn/problems/freedom-trail/
     */

    public static int findRotateSteps(String ring, String key) {
        // 每个字符所对应转盘上的下标
        Map<Character, List<Integer>> charMap = new HashMap<>();
        int len = ring.length();
        char[] ringChar = ring.toCharArray();
        for (int i = 0; i < len; i++) {
            if (!charMap.containsKey(ringChar[i])) {
                charMap.put(ringChar[i], new ArrayList<>());
            }
            charMap.get(ringChar[i]).add(i);
        }
        int targetLen = key.length();
        // dp[][] 初始化为-1
        int[][] dp = new int[len][targetLen + 1];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= targetLen; j++) {
                dp[i][j] = -1;
            }
        }
        return process(0, 0, key.toCharArray(), charMap, len, dp);
    }

    /**
     * 返回执行完目标所用的步数
     * @param pre     上一个按钮所在的位置
     * @param index   当前按钮所在目标串的位置
     * @param str     目标串
     * @param charMap 转盘字符所在下标
     * @param len     转盘的长度
     * @param dp      dp缓存
     * @return 所用步数
     */
    public static int process(int pre, int index, char[] str, Map<Character, List<Integer>> charMap, int len, int[][] dp) {
        if (dp[pre][index] != -1) {
            return dp[pre][index];
        }
        int ans = Integer.MAX_VALUE;
        if (index == str.length) {
            ans = 0;
        } else {
            List<Integer> charList = charMap.get(str[index]);
            for (Integer next : charList) {
                // 当前拨号需要的步数 + 确认的步数 + 剩下的字符所需要的步数
                int cost = dial(pre, next, len) + 1 + process(next, index + 1, str, charMap, len, dp);
                ans = Math.min(ans, cost);
            }
        }
        dp[pre][index] = ans;
        return ans;
    }

    /**
     * 从转盘上的l1到l2位置需要划过的最小步数
     * @param l1   开始位置
     * @param l2   结束位置
     * @param size 转盘的大小
     * @return 最小步数
     */
    public static int dial(int l1, int l2, int size) {
        // Math.abs(l1 - l2) 顺时针需要划过的步数
        // Math.min(l1, l2) + size - Math.max(l1, l2) 逆时针需要划过的步数
        return Math.min(Math.abs(l1 - l2), Math.min(l1, l2) + size - Math.max(l1, l2));
    }
}
