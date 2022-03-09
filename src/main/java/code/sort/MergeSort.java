package code.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 归并排序的时间复杂度为O(N*logN)
 * @author: wxm
 * @created: 2022/03/09
 */
public class MergeSort {

    /**
     * 归并排序,递归方案
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        process(arr, 0, arr.length - 1);
        return arr;
    }

    public static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] tempArr = new int[right - left + 1];
        int index = 0;
        int p1 = left;
        int p2 = mid + 1;
        // 谁小谁赋值
        while (p1 <= mid && p2 <= right) {
            tempArr[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 右边越界
        while (p1 <= mid) {
            tempArr[index++] = arr[p1++];
        }
        // 左边越界
        while (p2 <= right) {
            tempArr[index++] = arr[p2++];
        }
        for (int i = 0; i < tempArr.length; i++) {
            arr[left + i] = tempArr[i];
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int size = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) ((Math.random() - Math.random()) * (maxValue + 1));
        }
        return arr;
    }

    // for test
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
        int[] arr = generateRandomArray(20, 50);
        int[] copyArr = copyArray(arr);
        Arrays.sort(copyArr);
        int[] result = mergeSort(arr);
        for (int i = 0; i <copyArr.length; i++) {
            if (copyArr[i] != result[i]){
                System.out.println("fuck....");
            }
        }
    }
}
