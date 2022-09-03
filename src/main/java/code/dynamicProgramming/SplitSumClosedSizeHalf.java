package code.dynamicProgramming;

/**
 * @author wxm
 * @created 2022/9/2
 */
public class SplitSumClosedSizeHalf {

    /**
     * 给定一个正数数组arr，请把arr中所有的数分成两个集合
     * 如果arr长度为偶数，两个集合包含数的个数要一样多
     * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
     * 请尽量让两个集合的累加和接近
     * 返回最接近的情况下，较小集合的累加和
     */

    // 暴力递归
    public static int minSumClosed(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        if ((arr.length & 1) == 0) {
            return process(arr, 0, arr.length >> 1, sum >> 1);
        } else {
            int r1 = process(arr, 0, arr.length >> 1, sum >> 1);
            int r2 = process(arr, 0, (arr.length >> 1) + 1, sum >> 1);
            return Math.max(r1, r2);
        }
    }

    // arr[i....]自由选择，挑选的个数一定要是num个，累加和<=rest, 离rest最近的返回
    public static int process(int[] arr, int index, int num, int rest) {
        if (index == arr.length) {
            return num == 0 ? 0 : -1;
        } else {
            // 要当前数字
            int p1 = process(arr, index + 1, num, rest);
            int p2 = -1;
            int next = -1;
            // 不要当前数字
            if (rest >= arr[index]) {
                next = process(arr, index + 1, num - 1, rest - arr[index]);
            }
            if (next != -1) {
                p2 = arr[index] + next;
            }
            return Math.max(p1, p2);
        }
    }

    // 动态规划
    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int len = arr.length;
        int numLen = (len + 1) >> 1;
        int[][][] dp = new int[len + 1][numLen + 1][sum + 1];

        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= numLen; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        for (int rest = 0; rest <= sum; rest++) {
            dp[len][0][rest] = 0;
        }

        for (int index = len - 1; index >= 0; index--) {
            for (int num = 0; num <= numLen; num++) {
                for (int rest = 0; rest <= sum; rest++) {
                    int p1 = dp[index + 1][num][rest];
                    int p2 = -1;
                    int next = -1;
                    if (num - 1 >= 0 && rest >= arr[index]) {
                        next = dp[index + 1][num - 1][rest - arr[index]];
                    }
                    if (next != -1) {
                        p2 = arr[index] + next;
                    }
                    dp[index][num][rest] = Math.max(p1, p2);
                }
            }
        }
        if ((arr.length & 1) == 0) {
            return dp[0][arr.length >> 1][sum];
        } else {
            int r1 = dp[0][arr.length >> 1][sum];
            int r2 = dp[0][(arr.length >> 1) + 1][sum];
            return Math.max(r1, r2);
        }
    }


    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = minSumClosed(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("fuck....");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
