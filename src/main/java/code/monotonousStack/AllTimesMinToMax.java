package code.monotonousStack;

import java.util.Stack;

/**
 * @author wxm
 * @created 2022/9/11
 */
public class AllTimesMinToMax {

    /**
     * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
     * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
     * 那么所有子数组中，这个值最大是多少？
     */


    // 暴力解
    public static int minMax1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    min = Math.min(min, arr[k]);
                }
                max = Math.max(max, sum * min);
            }
        }
        return max;
    }

    // 单调栈解法
    public static int minMax2(int[] arr) {
        int len = arr.length;
        // 预处理前缀和数组
        int[] sums = new int[len];
        sums[0] = arr[0];
        for (int i = 1; i < len; i++) {
            sums[i] += sums[i - 1] + arr[i];
        }
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            // sum * min => 以当前值为最小值sum所能扩到的最大值
            // 以pop中左右两边离它最近且比它小的数的区间为它为最小值的区间
            // 存在重复值直接跨过？重复值的最后存在的值会将之前算错的值纠正
            // arr [3,4,4,4,4,1]
            // 以3为最小值所能扩到sum的最大值为 (3+4+4+4+4) * 3 => 57
            // 扩到第一个4时，值为 (3+4) * 3 = 21 继续扩不影响值的修正
            // 即第一个4和所能扩到的最后位置的4共享最终能扩到的最大位置
            // 也可将用Stack<List<Integer>> 代替
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer pop = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[pop]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[len - 1] : (sums[len - 1] - sums[stack.peek()])) * arr[pop]);
        }
        return max;
    }

    // for test
    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (minMax1(arr) != minMax2(arr)) {
                System.out.println("fuck.....");
                break;
            }
        }
        System.out.println("test finish");
    }
}
