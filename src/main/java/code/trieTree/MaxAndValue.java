package code.trieTree;

/**
 * @author: wxm
 * @created: 2023/07/05
 */
public class MaxAndValue {

    /**
     * 给定一个非负数组成的数组，长度一定大于1，想知道数组中哪两个数&的结果最大，返回这个最大结果。
     * 要求时间复杂度O(N)，额外空间复杂度O(1)
     */


    /**
     * 暴力解 O(N^2)
     *
     * @param arr
     * @return
     */
    public static int maxAndValue1(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                max = Math.max(max, arr[i] & arr[j]);
            }
        }
        return max;
    }

    /**
     * 解法二：O(N)
     * 因为是正数，所以不用考虑符号位(31位)
     * 首先来到30位，假设剩余的数字有N个(整体)，看看这一位是1的数，有几个
     * 如果有0个、或者1个
     * 说明不管怎么在数组中选择，任何两个数&的结果在第30位上都不可能有1了
     * 答案在第30位上的状态一定是0，
     * 保留剩余的N个数，继续考察第29位，谁也不淘汰(因为谁也不行，干脆接受30位上没有1的事实)
     * 如果有2个，
     * 说明答案就是这两个数(直接返回答案)，因为别的数在第30位都没有1，就这两个数有。
     * 如果有>2个，比如K个
     * 说明答案一定只用在这K个数中去选择某两个数，因为别的数在第30位都没有1，就这K个数有。
     * 答案在第30位上的状态一定是1，
     * 只把这K个数作为剩余的数，继续考察第29位，其他数都淘汰掉
     * .....
     *
     * @param arr
     * @return
     */
    public static int maxAndValue2(int[] arr) {
        int M = arr.length;
        int ans = 0;
        for (int bit = 30; bit >= 0; bit--) {
            int index = 0;
            int temp = M;
            while (index < M) {
                // arr[index]不满足当前位为1，交换放入垃圾区
                if ((arr[index] & (1 << bit)) == 0) {
                    swap(arr, index, --M);
                } else {
                    index++;
                }
            }
            // 一轮交换下来，只剩两个数，两数&必为最大
            if (M == 2) {
                return arr[0] & arr[1];
            }
            // 非垃圾区小于两个数，相&比为0，将上一轮的非垃圾区恢复
            if (M < 2) {
                M = temp;
            } else {
                // 大于两个数，bit位上必为1，或进答案中
                ans |= (1 << bit);
            }
        }
        return ans;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * for test
     *
     * @param size
     * @param range
     * @return
     */
    public static int[] randomArray(int size, int range) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * range) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int range = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int size = (int) (Math.random() * maxSize) + 2;
            int[] arr = randomArray(size, range);
            int ans1 = maxAndValue1(arr);
            int ans2 = maxAndValue2(arr);
            if (ans1 != ans2) {
                System.out.println("fuck...");
            }
        }
        System.out.println("测试结束");
    }
}
