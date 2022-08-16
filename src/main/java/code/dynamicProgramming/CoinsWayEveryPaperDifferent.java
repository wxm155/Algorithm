package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/08/15
 */
public class CoinsWayEveryPaperDifferent {

    /**
     * arr是货币数组，其中的值都是正数。再给定一个正数aim。
     * 每个值都认为是一张货币， 即便是值相同的货币也认为每一张都是不同的，
     * 返回组成aim的方法数
     * 例如：arr = {1,1,1}，aim = 2
     * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2 一共就3种方法，所以返回3
     */

    public static int coinWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process1(arr, aim, 0);
    }

    // rest 剩余的钱数
    // index [0.....arr.length]
    public static int process1(int[] arr, int rest, int index) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        if (rest < 0) {
            return 0;
        }
        // 不要当前值
        int p1 = process1(arr, rest, index + 1);
        // 要当前值
        int p2 = process1(arr, rest - arr[index], index + 1);
        return p1 + p2;
    }

    // 动态规划
    public static int coinWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        int[][] dp = new int[aim + 1][len + 1];
        dp[0][len] = 1;
        for (int index = len - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[rest][index] = dp[rest][index + 1] + (rest - arr[index] < 0 ? 0 :dp[rest - arr[index]][index + 1]);
            }
        }
        return dp[aim][0];
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
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = coinWays2(arr, aim);
            if (ans1 != ans2) {
                System.out.println("fuck....");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
