package code.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author: wxm
 * @created: 2022/03/31
 */
public class CoverMax {

    /**
     * 给定一组线段的开始节点和结束节点，求重合线段的最大数，重合线段节点距离必须大于等于1
     *
     * 思路：以线段起始节点排序，其余线段的结束节点数大于某线段的开始节点数的个数为该节点的重合线段数
     * [1,7] = |______|
     * [2,6] =  |____|
     * [5,6] =     |_|
     * [3,4] =   |_|
     */

    public static int coverMax(int[][] arr) {
        // 封装为Line数组
        Line[] lines = new Line[arr.length];
        for (int i = 0; i < arr.length; i++) {
            lines[i] = new Line(arr[i][0], arr[i][1]);
        }
        // 根据起始节点排序
        Arrays.sort(lines, new LineComparator());
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            // 线段的结束节点大于线段的起始节点的个数为重合的线段数
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            // 堆中线段结束节点的个数为该线段的重合线段数
            max = Math.max(max, heap.size());
        }
        return max;
    }

    /**
     * line比较器
     */
    public static class LineComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    /**
     * 自定义头尾节点类
     */
    public static class Line {
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
