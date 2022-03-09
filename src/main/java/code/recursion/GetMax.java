package code.recursion;

/**
 * 给定一个数组，获取数组中的最大值,递归实现
 *
 * @author: wxm
 * @created: 2022/03/09
 */
public class GetMax {

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int startIndex, int endIndex) {

        if (startIndex == endIndex) {
            return arr[startIndex];
        }
        int mid = startIndex + ((endIndex - startIndex) >> 1);
        int left = process(arr, startIndex, mid);
        int right = process(arr, mid + 1, endIndex);
        return Math.max(left, right);
    }

    /**
     * for test
     *
     * @param maxSize
     * @param maxValue
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((Math.random() - Math.random()) * (maxValue + 1));
        }
        return arr;
    }

    public static int test(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {

        int[] arr = generateRandomArray(20, 50);
        int max1 = getMax(arr);
        int max2 = test(arr);
        if (max1 != max2) {
            System.out.println("fuck....");
        }

    }
}
