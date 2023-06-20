package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/06/20
 */
public class EditCost {

    /**
     * 编辑距离问题：又称Levenshtein距离（莱文斯坦距离也叫做Edit Distance），是指两个字串之间，
     * 由一个转成另一个所需的最少编辑操作次数，如果它们的距离越大，说明它们越是不同。
     * 许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。
     *
     * 应用：计算文本相似度
     */

    /**
     * 动态规划的样本对应模型，以结尾位置作为可能性划分
     * @param s1 字符串1
     * @param s2 字符串2
     * @param ic 插入一个字符串的代价
     * @param dc 删除一个字符串的代价
     * @param rc 替换一个字符串的代价
     * @return 最小花费成本
     */
    public static int minCost(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int len1 = arr1.length + 1;
        int len2 = arr2.length + 1;
        // dp[i][j] 表示arr1[0...i-1]转换成arr2[0...j-1]所花费的最小成本
        int[][] dp = new int[len1][len2];
        // dp[0][0] = 0
        for (int i = 1; i < len2; i++) {
            dp[0][i] = ic * i;
        }
        for (int i = 1; i < len1; i++) {
            dp[i][0] = dc * i;
        }
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                // arr1[0...i-1]的长度等于arr2[0...j-1]的长度
                // abc abc    abc abd
                dp[i][j] = dp[i - 1][j - 1] + (arr1[i - 1] == arr2[j - 1] ? 0 : rc);
                // arr1[0...i-1]的长度大于arr2[0...j-1]的长度
                // abcde abcd   abkfe abcf
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
                // arr1[0...i-1]的长度小于arr2[0...j-1]的长度
                // abtc abkcd
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
            }
        }
        return dp[len1 - 1][len2 - 1];
    }
}
