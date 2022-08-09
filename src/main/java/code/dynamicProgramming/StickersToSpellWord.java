package code.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2022/08/05
 */
public class StickersToSpellWord {

    /**
     * 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
     * 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
     * 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1 。
     * 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。
     *
     * 示例 1：
     * 输入： stickers = ["with","example","science"], target = "thehat"
     * 输出：3
     * 解释：
     * 我们可以使用 2 个 "with" 贴纸，和 1 个 "example" 贴纸。
     * 把贴纸上的字母剪下来并重新排列后，就可以形成目标 “thehat“ 了。
     * 此外，这是形成目标字符串所需的最小贴纸数量。
     *
     * 示例 2:
     * 输入：stickers = ["notice","possible"], target = "basicbasic"
     * 输出：-1
     * 解释：我们不能通过剪切给定贴纸的字母来形成目标“basicbasic”。
     *
     * 力扣：https://leetcode-cn.com/problems/stickers-to-spell-word/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
     */

    public static int minStickers(String[] stickers, String target) {
        if (stickers == null || stickers.length == 0 || target == null || target.length() == 0) {
            return -1;
        }
        int res = process1(stickers, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static int process1(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String newStr = minus(target, sticker);
            if (newStr.length() != target.length()) {
                min = Math.min(min, process1(stickers, target));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String target, String source) {

        char[] arr1 = target.toCharArray();
        char[] arr2 = source.toCharArray();
        int[] arr3 = new int[26];
        for (int i = 0; i < arr1.length; i++) {
            arr3[arr1[i] - 'a']++;
        }
        for (int i = 0; i < arr2.length; i++) {
            arr3[arr2[i] - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] != 0) {
                for (int j = 0; j < arr3[i]; j++) {
                    builder.append((char) arr3[i] + 'a');
                }
            }
        }
        return builder.toString();
    }

    // 动态规划解法一
    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char aChar : chars) {
                counts[i][aChar - 'a']++;
            }
        }
        int ans = process2(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    public static int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char aChar : target) {
            tcounts[aChar - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            int[] sticker = stickers[i];
            // 尝试第一张贴纸是谁,进行剪枝
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int num = tcounts[j] - sticker[j];
                        for (int k = 0; k < num; k++) {
                            builder.append((char)(j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    // 动态规划解法二
    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] chars = stickers[i].toCharArray();
            for (char aChar : chars) {
                counts[i][aChar - 'a']++;
            }
        }
        Map<String,Integer> dp = new HashMap<>();
        dp.put("",0);
        int ans = process3(counts, target,dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    public static int process3(int[][] stickers, String t,Map<String,Integer> dp) {
        if (dp.containsKey(t)){
            return dp.get(t);
        }
        if (t.length() == 0) {
            return 0;
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char aChar : target) {
            tcounts[aChar - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            int[] sticker = stickers[i];
            // 尝试第一张贴纸是谁
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int num = tcounts[j] - sticker[j];
                        for (int k = 0; k < num; k++) {
                            builder.append((char)(j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process3(stickers, rest,dp));
            }
        }
        int res = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t,res);
        return res;
    }

}
