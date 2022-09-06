package code.slidingwindow;

import java.util.LinkedList;

/**
 * @author wxm
 * @created 2022/9/5
 */
public class AllLessNumSubArray {

    /**
     * 给定一个整型数组arr，和一个整数num
     * 某个arr中的子数组sub，如果想达标，必须满足：
     * sub中最大值 – sub中最小值 <= num，
     * 返回arr中达标子数组的数量
     */

    // 暴力解
    public static int subArray(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        int res = 0;
        for (int left = 0; left < arr.length; left++) {
            for (int right = left; right < arr.length; right++) {
                int max = arr[left];
                int min = arr[left];
                for (int i = left + 1; i <= right; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= num) {
                    res++;
                }
            }
        }
        return res;
    }

    // 滑动窗口优化解
    public static int subArray2(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        int res = 0;
        int right = 0;
        LinkedList<Integer> maxList = new LinkedList<>();
        LinkedList<Integer> minList = new LinkedList<>();
        for (int left = 0; left < arr.length; left++) {
            while (right < arr.length) {
                while (!maxList.isEmpty() && arr[maxList.peekLast()] <= arr[right]){
                    maxList.pollLast();
                }
                maxList.addLast(right);
                while (!minList.isEmpty() && arr[minList.peekLast()] >= arr[right]){
                    minList.pollLast();
                }
                minList.addLast(right);
                // 最大值减最小值已经不满足情况了，后续不可能再满足情况
                if (arr[maxList.peekFirst()] - arr[minList.peekFirst()] > num) {
                    break;
                } else {
                    right++;
                }
                // 划过多少数即存在多少满足条件解
                res += right - left;
            }
            if (maxList.peekFirst() == left) {
                maxList.pollFirst();
            }
            if (minList.peekFirst() == left) {
                minList.pollFirst();
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = subArray(arr, sum);
            int ans2 = subArray2(arr, sum);
            if (ans1 != ans2) {
                System.out.println("fuck.....");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
