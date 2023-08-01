package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/07/27
 */
public class BooleanEvaluation {

    /**
     * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
     * 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
     * <p>
     * 示例 1:
     * 输入: s = "1^0|0|1", result = 0
     * 输出: 2
     * 解释: 两种可能的括号方法是
     * 1^(0|(0|1))
     * 1^((0|0)|1)
     * <p>
     * 示例 2:
     * 输入: s = "0&0&0&1^1|0", result = 1
     * 输出: 10
     * <p>
     * 提示：
     * 运算符的数量不超过 19 个
     * <p>
     * 力扣：https://leetcode.cn/problems/boolean-evaluation-lcci/
     */

    public int countEval(String s, int result) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        Info[][] dp = new Info[len][len];
        Info info = process(s.toCharArray(), 0, s.length() - 1, dp);
        return result == 1 ? info.t : info.f;
    }

    /**
     * L,R一定非0即1，不是逻辑符号
     * 1^0|0|1
     * 以每个逻辑运算符作为当前最后的运算符，计算结果为true或false的方法数
     *
     * @param str
     * @param L
     * @param R
     * @param dp
     * @return
     */
    public static Info process(char[] str, int L, int R, Info[][] dp) {
        if (dp[L][R] != null) {
            return dp[L][R];
        }
        if (L == R) {
            int trueNum = str[L] == '1' ? 1 : 0;
            int falseNum = str[L] == '0' ? 1 : 0;
            return new Info(trueNum, falseNum);
        }
        int trueNum = 0, falseNum = 0;
        for (int spilt = L + 1; spilt < R; spilt += 2) {
            Info left = process(str, L, spilt - 1, dp);
            Info right = process(str, spilt + 1, R, dp);
            switch (str[spilt]) {
                case '&':
                    trueNum += left.t * right.t;
                    falseNum += left.t * right.f + left.f * right.t + left.f * right.f;
                    break;
                case '|':
                    trueNum += left.t * right.t + left.t * right.f + left.f * right.t;
                    falseNum += left.f * right.f;
                    break;
                case '^':
                    trueNum += left.t * right.f + left.f * right.t;
                    falseNum += left.t * right.t + left.f * right.f;
                    break;
            }
        }
        Info info = new Info(trueNum, falseNum);
        dp[L][R] = info;
        return info;
    }

    /**
     * 动态规划解法
     *
     * @param s
     * @param result
     * @return
     */
    public static int countEval2(String s, int result) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] exp = s.toCharArray();
        int N = exp.length;
        int[][][] dp = new int[2][N][N];
        dp[0][0][0] = exp[0] == '0' ? 1 : 0;
        dp[1][0][0] = dp[0][0][0] ^ 1;
        for (int i = 2; i < exp.length; i += 2) {
            dp[0][i][i] = exp[i] == '1' ? 0 : 1;
            dp[1][i][i] = exp[i] == '0' ? 0 : 1;
            for (int j = i - 2; j >= 0; j -= 2) {
                for (int k = j; k < i; k += 2) {
                    if (exp[k + 1] == '&') {
                        dp[1][j][i] += dp[1][j][k] * dp[1][k + 2][i];
                        dp[0][j][i] += (dp[0][j][k] + dp[1][j][k]) * dp[0][k + 2][i] + dp[0][j][k] * dp[1][k + 2][i];
                    } else if (exp[k + 1] == '|') {
                        dp[1][j][i] += (dp[0][j][k] + dp[1][j][k]) * dp[1][k + 2][i] + dp[1][j][k] * dp[0][k + 2][i];
                        dp[0][j][i] += dp[0][j][k] * dp[0][k + 2][i];
                    } else {
                        dp[1][j][i] += dp[0][j][k] * dp[1][k + 2][i] + dp[1][j][k] * dp[0][k + 2][i];
                        dp[0][j][i] += dp[0][j][k] * dp[0][k + 2][i] + dp[1][j][k] * dp[1][k + 2][i];
                    }
                }
            }
        }
        return dp[result][0][N - 1];
    }

    public static void main(String[] args) {
        String test = "1^0|0|1";
        System.out.println(countEval2(test, 0));
    }


    public static class Info {
        // 为true的方法数
        public int t;
        // 为false的方法数
        public int f;

        public Info(int trueNum, int falseNum) {
            this.t = trueNum;
            this.f = falseNum;
        }
    }
}
