package code.monotonousStack;

import java.util.Stack;

/**
 * @author wxm
 * @created 2022/9/11
 */
public class LargestRectangleInHistogram {

    /**
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     *
     * 力扣：https://leetcode.cn/problems/largest-rectangle-in-histogram/
     */

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0){
            return 0;
        }
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        // 以heights[i]做高，所能扩出的最大面积
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                int height = stack.pop();
                // 为空取-1方便计算
                int startIndex = stack.isEmpty() ? -1 : stack.peek();
                // 计算当前i的前一个数所形成的宽度
                max =  Math.max(max,(i - 1 - startIndex) * heights[height]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int height = stack.pop();
            int startIndex = stack.isEmpty() ? -1 : stack.peek();
            max =  Math.max(max,(heights.length - 1 - startIndex) * heights[height]);
        }
        return max;
    }

    // 数组实现栈
    public static int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int len = heights.length;
        int[] stack = new int[len];
        int point = -1;
        int max = 0;
        for (int i = 0; i < len; i++) {
            while (point != -1 && heights[stack[point]] >= heights[i]) {
                int height = stack[point--];
                int startIndex = point == -1 ? -1 : stack[point];
                max = Math.max(max, (i - 1 - startIndex) * heights[height]);
            }
            stack[++point] = i;
        }
        while (point != -1) {
            int height = stack[point--];
            int startIndex = point == -1 ? -1 : stack[point];
            max = Math.max(max, (heights.length - 1 - startIndex) * heights[height]);
        }
        return max;
    }
}
