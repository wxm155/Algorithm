package code.sort;

/**
 * @author: wxm
 * @created: 2022/03/11
 */
public class BiggerThanRightTwice {

    /**
     * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
     * 你需要返回给定数组中的重要翻转对的数量。
     * 示例：
     * 输入: [1,3,2,3,1]
     * 输出: 2
     * <p>
     * 力扣：https://leetcode-cn.com/problems/reverse-pairs/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
     *
     * 思路：{@link SmallSum} {@link ReverseOrderPair}
     */

    public static int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);
    }


    public static int process(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        int leftResult = process(arr, left, mid);
        int rightResult = process(arr, mid + 1, right);
        return leftResult + rightResult + merge(arr, left, mid, right);
    }


    public static int merge(int[] arr, int left, int mid, int right) {

        // 左半边和右半边进行比较
        int result = 0;
        int tempIndex = mid + 1;
        for (int i = left; i <= mid; i++) {
            // 防止int * 2越界
            while (tempIndex <= right && (long)arr[i] > (((long)arr[tempIndex]) << 1)) {
                tempIndex++;
            }
            result += tempIndex - mid - 1;
        }

        int[] tempArr = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int index = 0;
        while (p1 <= mid && p2 <= right) {
            tempArr[index++] = arr[p1] >= arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= mid) {
            tempArr[index++] = arr[p1++];
        }
        while (p2 <= right) {
            tempArr[index++] = arr[p2++];
        }
        for (int i = 0; i < tempArr.length; i++) {
            arr[left + i] = tempArr[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        int i = reversePairs(arr);
        System.out.println(i);
    }
}
