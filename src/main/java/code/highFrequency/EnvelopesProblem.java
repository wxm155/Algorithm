package code.highFrequency;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: wxm
 * @created: 2023/07/21
 */
public class EnvelopesProblem {

    /**
     * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
     * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     * 注意：不允许旋转信封。
     * <p>
     * 示例 1：
     * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出：3
     * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     * <p>
     * 示例 2：
     * 输入：envelopes = [[1,1],[1,1],[1,1]]
     * 输出：1
     * <p>
     * 提示：
     * <p>
     * 1 <= envelopes.length <= 10^5
     * envelopes[i].length == 2
     * 1 <= wi, hi <= 10^5
     * <p>
     * 力扣：https://leetcode.cn/problems/russian-doll-envelopes/
     */

    /**
     * 按找宽度升序，高度降序排序，求高度的最长子序列
     *
     * @param envelopes
     * @return
     */
    public static int maxEnvelopes(int[][] envelopes) {
        Envelop[] arr = sort(envelopes);
        int[] dp = new int[envelopes.length];
        dp[0] = arr[0].height;
        int max = 0;
        for (int i = 1; i < envelopes.length; i++) {
            int left = 0, right = max;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (arr[i].height > dp[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            max = Math.max(left, max);
            dp[left] = arr[i].height;
        }
        return max + 1;
    }

    public static Envelop[] sort(int[][] envelopes) {
        Envelop[] arr = new Envelop[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            arr[i] = new Envelop(envelopes[i][0], envelopes[i][1]);
        }
        Arrays.sort(arr, new EnvelopComparator());
        return arr;
    }

    public static class EnvelopComparator implements Comparator<Envelop> {

        @Override
        public int compare(Envelop o1, Envelop o2) {
            return (o1.width - o2.width) == 0 ? (o2.height - o1.height) : o1.width - o2.width;
        }
    }

    public static class Envelop {
        public int width;
        public int height;

        public Envelop(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }
}
