package code.trieTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2023/07/03
 */
public class MostXorZero {

    /**
     * 数组中所有数都异或起来的结果，叫做异或和。给定一个数组arr，可以任意切分成若干个不相交的子数组。
     * 其中一定存在一种最优方案，使得切出异或和为0的子数组最多，返回这个最多数量
     */

    /**
     * 时间复杂度 O(n ^ 2)
     *
     * @param arr 给定数组
     * @return 异或和为0的最多子数组数量
     */
    public static int mostXor1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 预处理前缀异或和数组
        int[] xor = new int[arr.length];
        xor[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            xor[i] = arr[i] ^ xor[i - 1];
        }
        return process1(xor, 1, new ArrayList<>());
    }

    /**
     * 以index决定，以前的部分以index结束，放进parts中，不以index结束，不放
     *
     * @param xor   前缀和数组
     * @param index 当前位置
     * @param parts 结束索引集合
     * @return 最多划分的数量
     */
    public static int process1(int[] xor, int index, List<Integer> parts) {
        int ans = 0;
        if (index == xor.length) {
            parts.add(index);
            ans = xorZeroPart(xor, parts);
            // 恢复现场
            parts.remove(parts.size() - 1);
        } else {
            // 不以index为分割
            int p1 = process1(xor, index + 1, parts);
            // 以index为分割
            parts.add(index);
            int p2 = process1(xor, index + 1, parts);
            // 恢复现场
            parts.remove(parts.size() - 1);
            ans = Math.max(p1, p2);
        }
        return ans;
    }

    public static int xorZeroPart(int[] xor, List<Integer> parts) {
        int ans = 0;
        int left = 0;
        // 左闭右开
        for (Integer right : parts) {
            if ((xor[right - 1] ^ (left == 0 ? 0 : xor[left - 1])) == 0) {
                ans++;
            }
            left = right;
        }
        return ans;
    }

    /**
     * 动态规划解法 O(n^2)
     * @param arr
     * @return
     */
    public static int mostXor2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // dp[i] => arr[0..i]上划分的异或和为0的子数组的最多数量
        int[] dp = new int[arr.length];
        // key : 异或和
        // value : 最晚出现的位置
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                int pre = map.get(xor);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i], dp[i - 1]);
            }
            map.put(xor, i);
        }
        return dp[arr.length - 1];
    }


    /**
     * for test
     *
     * @param maxSize  最大size
     * @param maxValue 最大值
     * @return 随机数组
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    /**
     * 打印数组
     *
     * @param arr 数组
     */
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 150000;
        int maxSize = 12;
        int maxValue = 5;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostXor2(arr);
            int comp = mostXor1(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fuck....");
    }
}
