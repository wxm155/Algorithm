package code.day01;

/**
 * @author: wxm
 * @created: 2022/03/03
 */
public class Code03_IsExist {

    /**
     * 给定一个有序数组和一个数，判断数组中是否存在该数
     */

    /**
     * 二分法
     * @param sortedArr
     * @param num
     * @return
     */
    public static boolean isExist(int[] sortedArr, int num) {
        // 判空
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int start = 0;
        int end = sortedArr.length - 1;
        int mid;
        while (start <= end) {
            // 等同于 (start + end)/2,此方案当数组的length为01111111 11111111 11111111 11111111时计算会出错
            mid = start + ((end - start) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }

    /**
     * for test
     * @param sortedArr
     * @param num
     * @return
     */
    public static boolean test(int[] sortedArr, int num) {
        for (int value : sortedArr) {
            if (value == num) {
                return true;
            }
        }
        return false;
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
            arr[i] = (int)((Math.random() - Math.random()) * (maxValue + 1));
        }
        return arr;
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        // 保证 0 ~ i 之间有序
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    /**
     * 交换两个数
     * i和j是一个位置会出错
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 500000; i++) {
            int[] ints = generateRandomArray(20, 50);
            insertSort(ints);
            boolean exist = isExist(ints, 10);
            boolean test = test(ints, 10);
            if (exist == test) {
//                System.out.println("success.....");
            } else {
                System.out.println("fuck....");
            }
        }
    }
}
