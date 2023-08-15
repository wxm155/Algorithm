package code.dynamicProgramming;

import java.text.DecimalFormat;

/**
 * @author: wxm
 * @created: 2023/08/11
 */
public class NCardsABWin {

    /**
     * 谷歌面试题:
     * 面值为1~10的牌组成一组，每次你从组里等概率的抽出1~10中的一张
     * 下次抽会换一个新的组，有无限组,当累加和<17时，你将一直抽牌
     * 当累加和>=17且<21时，你将获胜,当累加和>=21时，你将失败
     * 返回获胜的概率
     * <p>
     * 谷歌面试题扩展版，面值为1~N的牌组成一组，每次你从组里等概率的抽出1~N中的一张，
     * 下次抽会换一个新的组，有无限组
     * 当累加和<a时，你将一直抽牌；当累加和>=a且<b时，你将获胜；当累加和>=b时，你将失败
     * 返回获胜的概率，给定的参数为N，a，b
     */

    // 暴力解
    public static double win(int N, int a, int b) {
        if (a >= b || a < 0 || N < 0) {
            return 0.0;
        }
        if (b - a >= N) {
            return 1.0;
        }
        return process(N, 0, a, b);
    }

    public static double process(int N, int cur, int a, int b) {
        if (cur >= a && cur < b) {
            return 1.0;
        }
        if (cur >= b) {
            return 0.0;
        }
        double res = 0.0;
        for (int i = 1; i <= N; i++) {
            res += process(N, cur + i, a, b);
        }
        return res / N;
    }

    // 递归版本优化解
    public static double win2(int N, int a, int b) {
        if (a >= b || a < 0 || N < 0) {
            return 0.0;
        }
        if (b - a >= N) {
            return 1.0;
        }
        return process2(N, 0, a, b);
    }

    public static double process2(int N, int cur, int a, int b) {
        if (cur >= a && cur < b) {
            return 1.0;
        }
        if (cur >= b) {
            return 0.0;
        }
        // N=5, cur=2, a=3, b=6
        // f(2) = f(3) + f(4) + f(5) + f(6) + f(7)
        // cur在a的左边，获胜的概率为(b - a) / N
        if (cur + 1 == a) {
            return 1.0 * (b - a) / N;
        }
        // 当cur离a很远时：
        // N=5, cur=3, a=100, b=200
        // f(3) = f(4) + f(5) + f(6) + f(7) + f(8)
        // f(4) = f(5) + f(6) + f(7) + f(8) + f(9)
        // => f(3) = f(4) + f(4) - f(9)
        // => f(i) = f(i + 1) + f(i + 1) - f(i + N + 1)
        // 当cur离a很近时：
        // N=5, cur=98, a=100, b=103
        // f(96) = f(97) + f(98) + f(99) + f(100) + f(101)
        // f(97) = f(98) + f(99) + f(100) + f(101) + f(102)
        // f(98) = f(99) + f(100) + f(101) + f(102) + f(103)
        // f(99) = f(100) + f(101) + f(102) + f(103) + f(104)
        // f(103)和f(104)获胜概率为0.0
        // => f(98) = f(99) + f(100) + f(101) + f(102)
        //    f(99) = f(100) + f(101) + f(102)
        // => f(98) = f(99) + f(99)
        // => f(i) = f(i + 1) + f(i + 1)
        double res = process2(N, cur + 1, a, b) + process2(N, cur + 1, a, b) * N;
        if (cur + N + 1 < b) {
            res -= process2(N, cur + N + 1, a, b);
        }
        return res / N;
    }

    // 动态规划版本
    public static double win3(int N, int a, int b) {
        if (a >= b || a < 0 || N < 0) {
            return 0.0;
        }
        if (b - a >= N) {
            return 1.0;
        }
        double[] dp = new double[b];
        for (int i = a; i < b; i++) {
            dp[i] = 1.0;
        }
        if (a - 1 >= 0) {
            dp[a - 1] = 1.0 * (b - a) / N;
        }
        for (int cur = a - 2; cur >= 0; cur--) {
            double res = dp[cur + 1] + dp[cur + 1] * N;
            if (cur + N + 1 < b) {
                res -= dp[cur + N + 1];
            }
            dp[cur] = res / N;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int N = 10;
        int a = 17;
        int b = 21;
        System.out.println("N = " + N + ", a = " + a + ", b = " + b);
        System.out.println(win(N, a, b));
        System.out.println(win2(N, a, b));
        System.out.println(win3(N, a, b));

        int maxN = 15;
        int maxM = 20;
        int testTime = 100000;
        System.out.println("测试开始");
        DecimalFormat df = new DecimalFormat("#.####");
        for (int i = 0; i < testTime; i++) {
            N = (int) (Math.random() * maxN);
            a = (int) (Math.random() * maxM);
            b = (int) (Math.random() * maxM);
            double ans2 = Double.parseDouble(df.format(win(N, a, b)));
            double ans3 = Double.parseDouble(df.format(win2(N, a, b)));
            double ans4 = Double.parseDouble(df.format(win3(N, a, b)));
            if (ans2 != ans3 || ans2 != ans4) {
                System.out.println("fuck...");
                System.out.println(N + " , " + a + " , " + b);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                break;
            }
        }
        System.out.println("测试结束");

        N = 10000;
        a = 67834;
        b = 72315;
        System.out.println("N = " + N + ", a = " + a + ", b = " + b + "时, 除了方法3外都超时");
        System.out.print("方法3答案: ");
        System.out.println(win3(N, a, b));
    }
}
