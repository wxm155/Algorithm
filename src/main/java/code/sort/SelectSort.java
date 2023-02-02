package code.sort;

import java.util.Arrays;

/**
 * 选择排序
 * @author: wxm
 * @created: 2022/03/04
 */
public class SelectSort {

    /**
     * 选择排序
     * 0 ~ N-1  找到最小值，在哪，放到0位置上
     * 1 ~ n-1  找到最小值，在哪，放到1位置上
     * 2 ~ n-1  找到最小值，在哪，放到2位置上
     * 时间复杂度：O(N^2)
     * @param arr
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            // 最小值就是自己时不交换
            if (minIndex != i){
                swap(arr, i, minIndex);
            }
        }
    }

    /**
     *  交换
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];

    }

    /**
     * 生成随机数组
     *
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int size = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) ((Math.random() - Math.random()) * (maxValue + 1));
        }
        return arr;
    }

    /**
     * 判断两个数组是否相等  for test
     *
     * @param arr1
     * @param arr2
     * @return
     */
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

    /**
     * 复制数组 for test
     *
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 500000; i++) {
            int[] ints = generateRandomArray(10, 50);
            int[] copyArray = copyArray(ints);
            selectSort(ints);
            Arrays.sort(copyArray);
            boolean success = isEqual(ints, copyArray);
            if (!success) {
                System.out.println("fuck.....");
            }
        }
//        int[] arr = {-1,2,-4,17,17,};
//        selectSort(arr);
    }
}
