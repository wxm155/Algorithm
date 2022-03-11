package code.sort;

/**
 * @author: wxm
 * @created: 2022/03/11
 */
public class ReverseOrderPair {

    /**
     * 给定一个数组，右边的数小于左边为一组逆序对，求出数组中的逆序对有多少？
     * <p>
     * 思路：利用归并排序，将数组有序，右边有多少个小于左边指定的数，时间复杂度 N*logN
     * 是同一个考点{@link SmallSum}
     */

    /**
     * 归并排序解法
     *
     * @param arr
     * @return
     */
    public static int reverseOrderPair(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }


    public static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        int leftResult = process(arr, left, mid);
        int rightResult = process(arr, mid + 1, right);
        return leftResult + rightResult + DescMerge(arr, left, mid, right);
    }

    /**
     * 正解
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @return
     */
    public static int DescMerge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int p1 = mid;
        int p2 = right;
        int index = temp.length - 1;
        int result = 0;
        // 倒序操作，确定小于值的位置
        while (p1 >= left && p2 > mid) {
            result += arr[p1] > arr[p2] ? (p2 - mid) : 0;
            temp[index--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p2 > mid) {
            temp[index--] = arr[p2--];
        }
        while (p1 >= left) {
            temp[index--] = arr[p1--];
        }
        for (int i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }
        return result;
    }

    /**
     * -16 21 -33 -3
     * 只能逆序，正序结果可能会小于正确结果
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @return
     */
    public static int ascMerge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int index = 0;
        int result = 0;
        // 跳出while时可能还剩有左边大于右边的值
        while (p1 <= mid && p2 <= right) {
            result += arr[p1] > arr[p2] ? 1 : 0;
            temp[index++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= mid) {
            temp[index++] = arr[p1++];
        }
        while (p2 <= right) {
            temp[index++] = arr[p2++];
        }
        for (int i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }
        return result;
    }


    /**
     * 暴力解法  for test
     *
     * @param arr
     * @return
     */
    public static int test(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    result++;
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
        System.out.println("test start....");
        for (int i = 0; i < 50000; i++) {
            int[] array = generateRandomArray(5, 50);
            int[] copyArray = copyArray(array);
            int result1 = reverseOrderPair(array);
            int result2 = test(copyArray);
            if (result1 != result2) {
                System.out.println("fuck....");
            }
        }
        System.out.println("test end....");
    }

}
