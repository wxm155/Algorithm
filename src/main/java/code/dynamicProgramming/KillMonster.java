package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/08/29
 */
public class KillMonster {

    /**
     * 给定3个参数，N，M，K 怪兽有N滴血，
     * 等着英雄来砍自己 英雄每一次打击，都会让怪兽流失[0~M]的血量
     * 到底流失多少？每一次在[0~M]上等概率的获得一个值
     * 求K次打击之后，英雄把怪兽砍死的概率
     */

    // 暴力递归
    public static double kill(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long kill = process(N, K,M);
        long all = (long)Math.pow(M + 1,K);
        return (double)kill / (double) all;

    }

    public static long process(int blood, int rest, int M) {
        // 剩余次数为0
        if (rest == 0) {
            return blood <= 0 ? 1 : 0;
        }
        // 剩余血量小于等于0
        if (blood <= 0) {
            return (long) Math.pow(M + 1, rest);
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(blood - i, rest - 1, M);
        }
        return ways;
    }

    // 动态规划解法一
    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long[][] dp = new long[N + 1][K + 1];
        dp[0][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[0][rest] = (long) Math.pow(M + 1, rest);
            for (int blood = 1; blood <= N; blood++) {
                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (blood - i < 0) {
                        ways += (long) Math.pow(M + 1, rest - 1);
                    } else {
                        ways += dp[blood - i][rest - 1];
                    }
                }
                dp[blood][rest] = ways;
            }
        }
        long kill = dp[N][K];
        long all = (long) Math.pow(M + 1, K);
        return (double) kill / (double) all;
    }

    // 动态规划解法二，位置依赖优化
    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long[][] dp = new long[N + 1][K + 1];
        dp[0][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[0][rest] = (long) Math.pow(M + 1, rest);
            for (int blood = 1; blood <= N; blood++) {
                dp[blood][rest] = dp[blood - 1][rest] + dp[blood][rest - 1];
                // 减去多加的
                if (blood - 1 - M > 0) {
                    dp[blood][rest] -= dp[blood - 1 - M][rest - 1];
                } else {
                    dp[blood][rest] -= (long) Math.pow(M + 1, rest - 1);
                }
            }
        }
        long kill = dp[N][K];
        long all = (long) Math.pow(M + 1, K);
        return (double) kill / (double) all;
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = kill(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("fuck...");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
