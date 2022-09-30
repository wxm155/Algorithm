package code.monotonousStack;

import java.util.LinkedList;

/**
 * @author wxm
 * @created 2022/9/30
 */
public class MaxSumLengthNoMore {

    /**
     * 给定一个数组arr，和一个正数M，返回在arr的子数组在长度不超过M的情况下，最大的累加和
     */

    // O(N^2) 暴力解
    public static int getMaxSum(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        for (int L = 0; L < arr.length; L++) {
            int sum = 0;
            for (int R = L; R < arr.length; R++) {
                if (R - L + 1 > M) {
                    break;
                }
                sum += arr[R];
                res = Math.max(res, sum);
            }
        }
        return res;
    }

    // 滑动窗口加单调栈 O(N)
    public static int getMaxSum1(int[] arr, int M) {
        if (arr == null || arr.length == 0 || M < 1) {
            return 0;
        }
        int len = arr.length;
        int[] sum = new int[len];
        // 预处理前缀和数组
        sum[0] = arr[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        LinkedList<Integer> qMax = new LinkedList<>();
        int i = 0;
        // 生成第一个子数组
        int end = Math.min(len, M);
        for (; i < end; i++) {
            while (!qMax.isEmpty() && sum[qMax.peekLast()] <= sum[i]) {
                qMax.pollLast();
            }
            qMax.addLast(i);
        }
        int max = sum[qMax.peekFirst()];
        int L = 0;
        for (; i < len; L++, i++) {
            // 向右滑动弹出区间最大为划出窗口的值
            if (qMax.peekFirst() == L) {
                qMax.pollFirst();
            }
            while (!qMax.isEmpty() && sum[qMax.peekLast()] <= sum[i]) {
                qMax.pollLast();
            }
            qMax.addLast(i);
            max = Math.max(max, sum[qMax.peekFirst()] - sum[L]);
        }
        // 窗口右边滑到边界，左边继续向右滑
        for (; L < len - 1; L++) {
            if (qMax.peekFirst() == L) {
                qMax.pollFirst();
            }
            max = Math.max(max, sum[qMax.peekFirst()] - sum[L]);
        }
        return max;
    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static void main(String[] args) {
        int maxN = 50;
        int maxValue = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxN);
            int M = (int) (Math.random() * maxN);
            int[] arr = randomArray(N, maxValue);
            int ans1 = getMaxSum(arr, M);
            int ans2 = getMaxSum1(arr, M);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("fuck....");
            }
        }
        System.out.println("测试结束");
    }

}
