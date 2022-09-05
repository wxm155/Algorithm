package code.slidingwindow;

import java.util.LinkedList;

/**
 * @author wxm
 * @created 2022/9/5
 */
public class SlidingWindowMaxArray {

    /**
     * 假设一个固定大小为W的窗口，依次划过arr，
     * 返回每一次滑出状况的最大值
     * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
     * 返回：[5,5,5,4,6,7]
     */

    // 暴力解
    public static int[] right(int[] arr, int w) {
        if (arr == null || arr.length < w || w < 1){
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int left = 0;
        int right = w - 1;
        int index = 0;
        while (right < arr.length) {
            int max = arr[left];
            for (int i = left + 1; i <= right; i++) {
                max = Math.max(arr[i], max);
            }
            right++;
            left++;
            res[index++] = max;
        }
        return res;
    }

    // 滑动窗口优化解
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length < w || w < 1) {
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        // 存放数组下标
        LinkedList<Integer> list = new LinkedList<>();
        for (int right = 0; right < arr.length; right++) {
            // 从尾部移除小于等于arr[right]的值
            while (!list.isEmpty() && arr[list.peekLast()] <= arr[right]) {
                list.pollLast();
            }
            // 添加在末尾
            list.addLast(right);
            // 形成窗口后每移动一次弹出头部索引与窗口左边索引相等的值
            if (list.peekFirst() == right - w) {
                list.pollFirst();
            }
            // 形成窗口后每移动一次将头部的最大值添加到结果集
            if (right >= w - 1) {
                res[index++] = arr[list.peekFirst()];
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = right(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("fuck.....");
            }
        }
        System.out.println("test finish");
    }


}
