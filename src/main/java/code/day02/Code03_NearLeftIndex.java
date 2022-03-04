package code.day02;

import java.util.Arrays;

/**
 * @author: wxm
 * @created: 2022/03/04
 */
public class Code03_NearLeftIndex {

    /**
     * 给定一个有序数组，找出 >= N 的左位置，二分法
     */
    public static int nearLeft(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        int index = -1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (arr[mid] >= num) {
                index = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return index;
    }

    public static int test(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                return i;
            }
        }
        return -1;
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

    public static void main(String[] args) {
        for (int i = 0; i < 50000; i++) {
            int[] ints = generateRandomArray(20, 50);
            Arrays.sort(ints);
            int num = (int) ((50 + 1) * Math.random()) - (int) (50 * Math.random());
            int result = nearLeft(ints, num);
            int test = test(ints, num);
            if (result != test) {
                System.out.println("fuck....");
            }
        }
    }
}
