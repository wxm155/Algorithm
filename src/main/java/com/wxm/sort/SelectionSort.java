package com.wxm.sort;

/**
 * @author: wxm
 * @created: 2022/02/09
 */
public class SelectionSort {

    /**
     * 选择排序
     */

    public static void main(String[] args) {

        int[] arr = {1,9,5,5,4,1,2,56,87,2};
        sort(arr);
        for (int i = 0; i < arr.length; i ++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void sort(int[] arr){
        // 边界判断
        if (arr == null || arr.length < 2){
            return;
        }
        for(int i = 0; i < arr.length; i++){
            int minValueIndex = i;
            for (int j = i + 1; j < arr.length; j++){
                minValueIndex = arr[minValueIndex] < arr[j] ? minValueIndex : j;
            }
            // 交换
            if (minValueIndex != i){
                int temp = arr[minValueIndex];
                arr[minValueIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
