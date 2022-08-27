package code.dynamicProgramming;

/**
 * @author wxm
 * @created 2022/8/27
 */
public class CoinsWayNoLimit {

    /**
     * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
     * 每个值都认为是一种面值，且认为张数是无限的。
     * 返回组成aim的方法数
     * 例如：arr = {1,2}，aim = 4
     * 方法如下：1+1+1+1、1+1+2、2+2
     * 一共就3种方法，所以返回3
     */

    /**
     * 暴力递归解法
     * @param arr
     * @param aim
     * @return
     */
    public static int coinWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr,0,aim);
    }

    public static int process1(int[] arr, int index, int rest) {
        // 没钱了
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int num = 0; num * arr[index] <= rest; num++) {
            ways += process1(arr, index + 1,rest - (num * arr[index]));
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
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int num = 0; num * arr[index] <= rest; num++) {
                    ways += dp[index + 1][rest - (num * arr[index])];
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
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 防止dp越界
                int temp = rest - arr[index];
                // dp[index][rest] = dp[index + 1][rest] + dp[index + 1][rest - num * arr[index]] + .... 由num的数量决定
                // dp[index][rest - 1 * arr[index]] = dp[index + 1][1 * arr[index]] + dp[index + 1][rest - num * arr[index]] + ...
                // 即 dp[index][rest] = dp[index][rest - 1 * arr[index]] + dp[index + 1][rest]
                dp[index][rest] = temp >= 0 ? dp[index][temp] + dp[index + 1][rest] : dp[index + 1][rest];
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

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 ||ans1 != ans3) {
                System.out.println("Oops!");
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
