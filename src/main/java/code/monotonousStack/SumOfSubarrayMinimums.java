package code.monotonousStack;

/**
 * @author wxm
 * @created 2022/9/15
 */
public class SumOfSubarrayMinimums {

    /**
     * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
     * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
     *
     * 示例 1：
     * 输入：arr = [3,1,2,4]
     * 输出：17
     * 解释：
     * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
     * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
     *
     * 示例 2：
     * 输入：arr = [11,81,94,43,3]
     * 输出：444
     *  
     * 提示：
     * 1 <= arr.length <= 3 * 104
     * 1 <= arr[i] <= 3 * 104
     *
     * 力扣：https://leetcode.cn/problems/sum-of-subarray-minimums/
     */

    // 暴力解，超过时间限制
    public int sumSubarrayMins(int[] arr) {
        long res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = arr[i];
                for (int k = i + 1; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                }
                res += min;
            }
        }
        return (int) (res % 1000000007);
    }

    // 无单调栈暴力解
    // 以arr[i]为最小值，范围内的子数组的个数 * arr[i]
    public int sumSubarrayMins2(int[] arr) {
        int[] left = leftNearLessEqual(arr);
        int[] right = rightNearLess(arr);
        long res = 0;
        for (int i = 0; i < arr.length; i++) {
            int start = i - left[i];
            int end = right[i] - i;
            res += start * end * (long) arr[i];
        }
        return (int) (res % 1000000007);
    }

    // int[x] = y  => 离x左边最近且小于等于它的位置在y
    public static int[] leftNearLessEqual(int[] arr) {
        int len = arr.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int min = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i]) {
                    min = j;
                    break;
                }
            }
            res[i] = min;
        }
        return res;
    }

    // int[x] = y  => 离x右边最近且小于它的位置在y
    public static int[] rightNearLess(int[] arr) {
        int len = arr.length;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int min = len;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[i]) {
                    min = j;
                    break;
                }
            }
            res[i] = min;
        }
        return res;
    }

    // 单调栈加速
    // 例：2 4 5 3 6 6 3 2
    // 以第一个3为最小值能形成的子数组
    // 以4开头的子数组
    //      4 5 3
    //      4 5 3 6
    //      4 5 3 6 6
    //      4 5 3 6 6 3
    // 以5开头的子数组
    //        5 3
    //        5 3 6
    //        5 3 6 6
    //        5 3 6 6 3
    // 以3开头的子数组
    //          3
    //          3 6
    //          3 6 6
    //          3 6 6 3
    // num = (3 - 0) * (7 - 3)
    // 左边包含等于左边不包含或左边不包含右边包含等效果且不会出现重复解
    public static int sumSubarrayMins3(int[] arr) {
        int len = arr.length;
        int[] stack = new int[len];
        int[] left = nearLessEqualLeft(arr, stack);
        int[] right = nearLessRight(arr, stack);
        long res = 0;
        for (int i = 0; i < len; i++) {
            int start = i - left[i];
            int end = right[i] - i;
            res += start * end * (long) arr[i];
        }
        return (int) (res % 1000000007);
    }

    public static int[] nearLessEqualLeft(int[] arr, int[] stack) {
        int len = arr.length;
        int[] left = new int[len];
        int index = -1;
        // 逆向遍历，入栈的值为栈顶的值左边离它最近且小于它的值
        for (int i = len - 1; i >= 0; i--) {
            while (index != -1 && arr[stack[index]] >= arr[i]) {
                left[stack[index--]] = i;
            }
            stack[++index] = i;
        }
        while (index != -1) {
            left[stack[index--]] = -1;
        }
        return left;
    }

    public static int[] nearLessRight(int[] arr, int[] stack) {
        int len = arr.length;
        int[] right = new int[len];
        int index = -1;
        for (int i = 0; i < len; i++) {
            while (index != -1 && arr[stack[index]] > arr[i]) {
                right[stack[index--]] = i;
            }
            stack[++index] = i;
        }
        while (index != -1) {
            right[stack[index--]] = len;
        }
        return right;
    }

    public static void main(String[] args) {
        int[] arr = {3,1,2,4};
        sumSubarrayMins3(arr);
    }
}
