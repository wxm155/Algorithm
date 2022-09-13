package code.monotonousStack;

import java.util.Stack;

/**
 * @author wxm
 * @created 2022/9/12
 */
public class MaximalRectangle {

    /**
     * 给定一个二维数组matrix，其中的值不是0就是1，
     * 返回全部由1组成的最大子矩形内部有多少个1（面积）
     *
     * 力扣：https://leetcode.cn/problems/maximal-rectangle/
     */

    /**
     * 将矩阵看成每一行的直方图面积问题，从上至下，都为1是高度相加，为0则重置高度
     * {@link LargestRectangleInHistogram}
     * @param matrix
     * @return
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] heights = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // 同列都为1高度相加，为0重置高度
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }
            maxArea = Math.max(maxArea, getMaximal(heights));
        }
        return maxArea;
    }

    public static int getMaximal(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int height = stack.pop();
                int startIndex = stack.isEmpty() ? -1 : stack.peek();
                maxArea = Math.max(maxArea, (i - 1 - startIndex) * heights[height]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int height = stack.pop();
            int startIndex = stack.isEmpty() ? -1 : stack.peek();
            maxArea = Math.max(maxArea, (heights.length - 1 - startIndex) * heights[height]);
        }
        return maxArea;
    }

    // 数组实现栈 比系统栈速度快
    public static int getMaximal2(int[] heights) {
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
