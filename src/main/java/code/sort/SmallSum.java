package code.sort;

/**
 * @author: wxm
 * @created: 2022/03/11
 */
public class SmallSum {

    /**
     * 给定一个数组，求出每个数左边小于它的数之和
     * <p>
     * 思路：利用归并排序，将数组有序，右边有多少个大于左边指定的数，时间复杂度 N*logN
     *      归并排序，利用小部分有序可以大大节省比较次数
     */


    /**
     * 归并排序解法
     * @param arr
     * @return
     */
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        int processLeft = process(arr, left, mid);
        int processRight = process(arr, mid + 1, right);
        return processLeft + processRight + merge(arr, left, mid, right);
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int result = 0;
        int index = 0;
        while (p1 <= mid && p2 <= right) {
            result += arr[p1] < arr[p2] ? arr[p1] * (right - p2 + 1) : 0;
            temp[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 左边越界
        while (p2 <= right) {
            temp[index++] = arr[p2++];
        }
        // 右边越界
        while (p1 <= mid) {
            temp[index++] = arr[p1++];
        }
        for (int i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }
        return result;
    }

    /**
     * 暴力解法  for test
     * @param arr
     * @return
     */
    public static int test(int[] arr){
        int result = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]){
                    result += arr[i];
                }
            }
        }
        return result;
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

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int size = (int) (Math.random() * (maxSize + 1));
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) ((Math.random() - Math.random()) * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50000; i++) {
            int[] array = generateRandomArray(5, 50);
            int[] copyArray = copyArray(array);
            int result1 = smallSum(array);
            int result2 = test(copyArray);
            if (result1 != result2){
                System.out.println("fuck....");
            }
        }
    }
}
