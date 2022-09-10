package code.monotonousStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author wxm
 * @created 2022/9/9
 */
public class MonotonousStack {

    /**
     * 单调栈：解决某一位置左边离它最近的小于或大于他的位置信息，右边离它最近的小于或大于他的位置信息问题
     */


    /**
     * 获取arr[]中每个数字左边和右边离它最近并且小于它的数
     * 注：arr[]中数字无重复值
     * @param arr
     * @return
     */
    public static int[][] getNearLessNoRepeat(int[] arr) {
        // mStack[i][0] 左边离它最近并且小于它的数的下标
        // mStack[i][1] 右边离它最近并且小于它的数的下标
        int[][] mStack = new int[arr.length][2];
        // 存储数组下标
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 栈中从下往上一次从小到大，入栈前发现栈顶的数小于入栈的数，
            // 即栈顶的数左边最近且离它最近的数为他的下一个数，
            // 右边离它最近且离它最近的数为入栈的数
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int cur = stack.pop();
                int preIndex = stack.isEmpty() ? -1 : stack.peek();
                mStack[cur][0] = preIndex;
                mStack[cur][1] = i;
            }
            stack.push(i);
        }
        // 剩余栈中的数
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int preIndex = stack.isEmpty() ? -1 : stack.peek();
            mStack[cur][0] = preIndex;
            mStack[cur][1] = -1;
        }
        return mStack;
    }

    /**
     * 获取arr[]中每个数字左边和右边离它最近并且小于它的数
     * 注：arr[]中数字有重复值
     * @param arr
     * @return
     */
    public static int[][] getNearLessDuplicateValue(int[] arr) {
        int[][] mStack = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> list = stack.pop();
                // list中最后的一个为最近的值
                int preIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer index : list) {
                    mStack[index][0] = preIndex;
                    mStack[index][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        // 栈中剩余的数
        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            int preIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer index : pop) {
                mStack[index][0] = preIndex;
                mStack[index][1] = -1;
            }
        }
        return mStack;
    }
}
