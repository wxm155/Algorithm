package code.leetCodeTop100;

import java.util.Arrays;

/**
 * @author: wxm
 * @created: 2024/01/30
 */
public class Merge {

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * <p>
     * 示例 1：
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * <p>
     * 示例 2：
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     * <p>
     * 提示：
     * 1 <= intervals.length <= 10^4
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 10^4
     * <p>
     * 力扣：https://leetcode.cn/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-100-liked
     */

    public int[][] merge(int[][] intervals) {
        int[][] res = new int[intervals.length][2];
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int temp = 0;
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                end = intervals[i][1];
            } else {
                res[temp][0] = start;
                res[temp++][1] = end;
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        res[temp][0] = start;
        res[temp][1] = end;
        return Arrays.copyOfRange(res, 0, temp + 1);
    }
}
