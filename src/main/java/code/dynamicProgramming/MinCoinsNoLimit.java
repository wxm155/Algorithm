package code.dynamicProgramming;

/**
 * @author wxm
 * @created 2022/9/1
 */
public class MinCoinsNoLimit {

    /**
     * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
     * 每个值都认为是一种面值，且认为张数是无限的。
     * 返回组成aim的最少货币数
     */

    /**
     * 暴力递归解法
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            // 还需要的张数
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }else {
            int ans = Integer.MAX_VALUE;
            for (int num = 0; arr[index] * num <= rest; num++) {
                int next = process(arr, index + 1, rest - arr[index] * num);
                if (Integer.MAX_VALUE != next) {
                    ans = Math.min(ans, num + next);
                }
            }
            return ans;
        }
    }

    /**
     * 动态规划解法一
     * @param arr
     * @param aim
     * @return
     */
    public static int dp1(int[] arr, int aim) {
        if (aim == 0){
            return 0;
        }
        int len = arr.length;
        int[][] dp = new int[len + 1][aim + 1];

        dp[len][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[len][i] = Integer.MAX_VALUE;
        }
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int num = 0; arr[index] * num <= rest; num++) {
                    int next = dp[index + 1][rest - arr[index] * num];
                    if (Integer.MAX_VALUE != next) {
                        ans = Math.min(ans, num + next);
                    }
                }
                dp[index][rest] = ans;
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
        if (aim == 0) {
            return 0;
        }
        int len = arr.length;
        int[][] dp = new int[len + 1][aim + 1];

        dp[len][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[len][i] = Integer.MAX_VALUE;
        }
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 分解为单张求解
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0 && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }

    // for test
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
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
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3= dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("fuck....");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

}
