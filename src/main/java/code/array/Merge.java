package code.array;

import java.util.*;

/**
 * @author: wxm
 * @created: 2022/04/02
 */
public class Merge {

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     * <p>
     * 示例 1：
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * <p>
     * 示例 2：
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     * <p>
     * 力扣：https://leetcode-cn.com/problems/merge-intervals/
     * <p>
     * 思路：排序，双指针
     */

    public static int[][] merge(int[][] intervals) {

        int[][] result = new int[intervals.length][2];
        int temp = 0;
        // 根据开始节点排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        // 起始指针
        int start = intervals[0][0];
        // 结束指针
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            // 下一个数的开始节点小于等于结束指针，继续
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            } else {
                // 无法继续
                result[temp][0] = start;
                result[temp++][1] = end;
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        // 最后一组数据
        result[temp][0] = start;
        result[temp][1] = end;
        return Arrays.copyOfRange(result, 0, temp + 1);
    }

    public static void main(String[] args) {
        int[][] array = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        merge(array);
    }

}
