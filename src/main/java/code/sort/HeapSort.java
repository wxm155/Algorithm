package code.sort;

import java.util.Arrays;

/**
 * @author: wxm
 * @created: 2022/03/30
 */
public class HeapSort {

    /**
     * 堆排序
     * 以数组模拟完全二叉树
     * 父节点 = (i - 1)/2
     * 左子节点 = (i * 2) + 1
     * 右子节点 = (i * 2) + 2
     */

    /**
     * 堆排序,大根堆
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 从上往下建堆 O(N*logN)
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }
        // 从下往上建堆 O(N)
        for (int i = arr.length - 1;i >= 0;i--){
            heapify(arr,i,arr.length);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    // arr[index]能否上移
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // arr[index]能否下移
    public static void heapify(int[] arr, int index, int heapSize) {
        // 左节点
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 取出左右节点最大值的下表
            int maxIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 左右节点最大值和父节点比较
            maxIndex = arr[maxIndex] > arr[index] ? maxIndex : index;
            if (maxIndex == index) {
                break;
            }
            swap(arr, maxIndex, index);
            index = maxIndex;
            left = index * 2 + 1;
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

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int size = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) ((Math.random() - Math.random()) * (maxValue + 1));
        }
        return arr;
    }

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

        for (int i = 0; i < 500000; i++) {
            int[] arr1 = generateRandomArray(20, 50);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("fuck....");
            }
        }
    }

}
