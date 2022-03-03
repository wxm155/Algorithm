package com.wxm.day01;

import java.util.Arrays;

/**
 * @author: wxm
 * @created: 2022/03/03
 */
public class Code04_InsertSort {

    /**
     * 插入排序写法一
     * @param arr
     */
    public static void insertSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int index = i - 1;
            while (index >= 0 && arr[index] > arr[index + 1]) {
                swap(arr, index, index + 1);
                index--;
            }
        }
    }

    /**
     * 插入排序写法二
     * @param arr
     */
    public static void insertSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    /**
     * 交换
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 生成随机数组
     * Math.random() ===> [0,1)
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
            int[] arr = generateRandomArray(20, 50);
            int[] ints = copyArray(arr);
            Arrays.sort(ints);
            insertSort1(arr);
            boolean success = isEqual(ints, arr);
            if (!success) {
                System.out.println("fuck....");
            }
        }
    }
}
