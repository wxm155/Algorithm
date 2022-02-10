package com.wxm.sort;

/**
 * @author: wxm
 * @created: 2022/02/09
 */
public class BubbleSort {


    public static void main(String[] args) {
        int[] arr = {1,9,5,5,4,1,2,56,87,2};
        sort(arr);
        for (int i = 0; i < arr.length; i ++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void sort(int[] arr){

        if (arr == null || arr.length < 2){
            return;
        }
        int temp;
        // 标志变量，表示是否进行过交换
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++){
            for (int j = 0; j < arr.length - 1 - i; j++){
                if (arr[j] > arr[j + 1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            // 一趟排序中，一次交换都没有发生过
            if (!flag){
                break;
            }else {
                // 重置标志变量
                flag = false;
            }
        }
    }
}
