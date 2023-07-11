package code.highFrequency;

import java.util.LinkedList;

/**
 * @author: wxm
 * @created: 2023/07/11
 */
public class ExpressionCompute {

    /**
     * 给定一个字符串str，str表示一个公式，公式里可能有整数、加减乘除符号和左右括号。返回公式的计算结果
     * 难点在于括号可能嵌套很多层，str="48*((70-65)-43)+8*1"，返回-1816。
     * str="3+1*4"，返回7。str="3+(1*4)"，返回7。
     * 1，可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查
     * 2，如果是负数，就需要用括号括起来，比如"4*(-3)"但如果负数作为公式的开头或括号部分的开头，则可以没有括号，
     * 比如"-3*4"和"(-3*4)"都是合法的
     * 3，不用考虑计算过程中会发生溢出的情况。
     * <p>
     * 力扣：https://leetcode.cn/problems/basic-calculator-iii/
     */

    public static int calculate(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        return process(s.toCharArray(), 0)[0];
    }

    /**
     * 表示arr[i....]执行表达式返回的结果
     * 遇到字符串终止活着右括号就结束
     *
     * @param arr 表达式字符数组
     * @param i   当前位置
     * @return int[0] 计算的结果，int[1] 计算结束的位置
     */
    public static int[] process(char[] arr, int i) {
        LinkedList<String> stack = new LinkedList<>();
        int cur = 0;
        while (i < arr.length && arr[i] != ')') {
            // 遇到数字
            if (arr[i] >= '0' && arr[i] <= '9') {
                cur = cur * 10 + arr[i++] - '0';
            } else if (arr[i] != '(') {
                // 遇到计算符号
                addNum(stack, cur);
                stack.addLast(String.valueOf(arr[i++]));
                cur = 0;
            } else {
                // 遇到右括号，交给子过程去计算
                int[] ints = process(arr, i + 1);
                cur = ints[0];
                i = ints[1] + 1;
            }
        }
        addNum(stack, cur);
        return new int[]{getNum(stack), i};
    }

    /**
     * 将数添加进栈中，如果是加减，跳过计算，如果是乘除，直接计算后加入栈中
     *
     * @param stack
     * @param num
     */
    public static void addNum(LinkedList<String> stack, int num) {
        if (!stack.isEmpty()) {
            int cur = 0;
            String stop = stack.pollLast();
            // 如果是加减，直接跳过
            if (stop.equals("+") || stop.equals("-")) {
                stack.addLast(stop);
            } else {
                // 先计算乘除
                cur = Integer.valueOf(stack.pollLast());
                num = stop.equals("*") ? (cur * num) : (cur / num);
            }
        }
        stack.addLast(String.valueOf(num));
    }

    /**
     * 计算栈中的结果，只剩加减计算
     *
     * @param stack
     * @return
     */
    public static int getNum(LinkedList<String> stack) {
        boolean add = true;
        int res = 0;
        while (!stack.isEmpty()) {
            String first = stack.pollFirst();
            if (first.equals("+")) {
                add = true;
            } else if (first.equals("-")) {
                add = false;
            } else {
                int cur = Integer.valueOf(first);
                res += add ? cur : -cur;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String test = "48*((70-65)-43)+8*1";
        System.out.println(calculate(test));
    }
}
