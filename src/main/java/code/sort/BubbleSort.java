package code.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author: wxm
 * @created: 2022/03/04
 */
public class BubbleSort {

    /**
     * 1、比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 2、每趟从第一对相邻元素开始，对每一对相邻元素作同样的工作，直到最后一对。
     * 3、针对所有的元素重复以上的步骤，除了已排序过的元素(每趟排序后的最后一个元素)，直到没有任何一对数字需要比较。
     * 冒泡排序 时间复杂度：O(N^2)
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    swap(arr, j, j + 1);
                }
            }
            // 一次排序过程中一次都没有发生交换，结束排序
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

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
            int[] ints = generateRandomArray(20, 50);
            int[] copyArray = copyArray(ints);
            bubbleSort(ints);
            Arrays.sort(copyArray);
            boolean success = isEqual(ints, copyArray);
            if (!success) {
                System.out.println("fuck.....");
            }
        }
    }
}
