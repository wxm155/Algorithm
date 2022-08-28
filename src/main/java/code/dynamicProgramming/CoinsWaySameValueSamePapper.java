package code.dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxm
 * @created 2022/8/28
 */
public class CoinsWaySameValueSamePapper {

    /**
     * arr是货币数组，其中的值都是正数。再给定一个正数aim。
     * 每个值都认为是一张货币， 认为值相同的货币没有任何不同，
     * 返回组成aim的方法数
     * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
     * 方法：1+1+1+1、1+1+2、2+2
     * 一共就3种方法，所以返回3
     */

    /**
     * 暴力递归解法
     * @param arr
     * @param aim
     * @return
     */
    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        CoinsInfo info = getInfo(arr);
        return process(info.coins, info.nums, 0, aim);
    }

    public static int process(int[] coins, int[] nums, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int num = 0; num * coins[index] <= rest && num <= nums[index]; num++) {
            ways += process(coins, nums, index + 1, rest - (num * coins[index]));
        }
        return ways;
    }

    /**
     * 动态规划解法一
     * @param arr
     * @param aim
     * @return
     */
    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        CoinsInfo info = getInfo(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
        int len = coins.length;
        int[][] dp = new int[len + 1][aim + 1];
        dp[len][0] = 1;
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int num = 0; num * coins[index] <= rest && num <= nums[index]; num++) {
                    ways += dp[index + 1][rest - (num * coins[index])];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    /**
     * 动态规划解法二，位置依赖优化
     * @param arr
     * @param aim
     * @return
     */
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        CoinsInfo info = getInfo(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
        int len = coins.length;
        int[][] dp = new int[len + 1][aim + 1];
        dp[len][0] = 1;
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                int restCoins = rest - coins[index];
                // 防止dp越界
                if (restCoins >= 0) {
                    dp[index][rest] += dp[index][restCoins];
                }
                // 剩余钱数不能被剩余货币张数全部匹配完，此方案不满足条件，减去相应的值
                int matchNum = rest - (coins[index] * (nums[index] + 1));
                if (matchNum >= 0) {
                    dp[index][rest] -= dp[index + 1][matchNum];
                }
            }
        }
        return dp[0][aim];
    }


    public static CoinsInfo getInfo(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int coin : arr) {
            if (!map.containsKey(coin)) {
                map.put(coin, 1);
            } else {
                map.put(coin, map.get(coin) + 1);
            }
        }
        int size = map.size();
        int[] coins = new int[size];
        int[] nums = new int[size];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            coins[index] = entry.getKey();
            nums[index++] = entry.getValue();
        }
        return new CoinsInfo(coins, nums);
    }

    public static class CoinsInfo {
        public int[] coins;
        public int[] nums;

        public CoinsInfo(int[] coins, int[] nums) {
            this.coins = coins;
            this.nums = nums;
        }
    }

    // for test
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("fuck...");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
