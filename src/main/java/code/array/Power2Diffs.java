package code.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: wxm
 * @created: 2023/07/07
 */
public class Power2Diffs {

    /**
     * 给定一个有序数组arr，其中值可能为正、负、0。返回arr中每个数都平方之后不同的结果有多少种？
     */

    /**
     * 时间复杂度：O(N),额外空间复杂度：O(N)
     *
     * @param arr
     * @return
     */
    public static int diff1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(Math.abs(arr[i]));
        }
        return set.size();
    }

    /**
     * 利用首尾双指针：时间复杂度：O(N),额外空间复杂度：O(1)
     * @param arr
     * @return
     */
    public static int diff2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        int left = 0;
        int right = arr.length - 1;
        int leftAbs = 0;
        int rightAbs = 0;
        while (left <= right) {
            ans++;
            leftAbs = Math.abs(arr[left]);
            rightAbs = Math.abs(arr[right]);
            if (leftAbs > rightAbs) {
                while (left < arr.length && Math.abs(arr[left]) == leftAbs) {
                    left++;
                }
            } else if (leftAbs < rightAbs) {
                while (right >= 0 && Math.abs(arr[right]) == rightAbs) {
                    right--;
                }
            } else {
                while (left < arr.length && Math.abs(arr[left]) == leftAbs) {
                    left++;
                }
                while (right >= 0 && Math.abs(arr[right]) == rightAbs) {
                    right--;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] randomSortedArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        Arrays.sort(ans);
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int cur : arr) {
            System.out.print(cur + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 100;
        int value = 500;
        int testTimes = 200000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomSortedArray(len, value);
            int ans1 = diff1(arr);
            int ans2 = diff2(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
