package code.dynamicProgramming;

/**
 * @author wxm
 * @created 2022/7/31
 */
public class RobotWalk {

    /**
     * 假设有排成一行的N个位置记为1~N，N一定大于或等于2
     * 开始时机器人在其中的M位置上(M一定是1~N中的一个)
     * 如果机器人来到1位置，那么下一步只能往右来到2位置；
     * 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
     * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
     * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
     * 给定四个参数 N、M、K、P，返回方法数
     */

    public static int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process1(start, K, aim, N);
    }

    /**
     * @param cur  当前位置
     * @param rest 剩余步数
     * @param aim  目标点
     * @param n    最右位置
     * @return 机器人从cur出发，经历rest步，最终到达aim的方法数
     */
    public static int process1(int cur, int rest, int aim, int n) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        // 如果在左边
        if (cur == 1) {
            return process1(2, rest - 1, aim, n);
        }
        // 如果在右边
        if (cur == n) {
            return process1(n - 1, rest - 1, aim, n);
        }
        // 如果在中间
        return process1(cur - 1, rest - 1, aim, n) + process1(cur + 1, rest - 1, aim, n);
    }

    /**
     * way1暴力递归，可能存在重复执行的过程，
     * 例：n = 5,start = 3,aim = 5,k = 4;
     * 递归的可变参数 cur、rest
     *           p(3,4)
     *          /      \
     *     p(2,3)      p(4,3)
     *     /   \        /    \
     * p(1,2)  p(3,2) p(3,2)  p(5,2)
     * <p>
     * 上述过程中出现了p(3,2)的重复递归过程，即存在可优化的地方
     */
    public static int ways2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        // 记录是否执行过的缓存
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(start, K, aim, N, dp);
    }

    /**
     * dp[cur][rest] 为计算过的值
     * @param cur  范围在[1 - n]
     * @param rest 范围在[0 - k]
     * @param aim
     * @param n
     * @return
     */
    public static int process2(int cur, int rest, int aim, int n, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, rest - 1, aim, n, dp);
        } else if (cur == n) {
            ans = process2(n - 1, rest - 1, aim, n, dp);
        } else {
            ans = process2(cur - 1, rest - 1, aim, n, dp) + process2(cur + 1, rest - 1, aim, n, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    /**
     * 动态规划
     * @param N
     * @param start
     * @param aim
     * @param K
     * @return
     */
    public static int ways3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            // dp[1][rest] 的值依赖于 dp[2][rest - 1]
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                // 中间值 dp[cur][rest] 依赖于 dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1]
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            // dp[N][rest] 的值依赖于 dp[N - 1][rest - 1]
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }


    public static void main(String[] args)  {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }

}
