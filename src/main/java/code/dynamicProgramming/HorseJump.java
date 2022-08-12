package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/08/12
 */
public class HorseJump {

    /**
     * 想象一个象棋的棋盘， 然后把整个棋盘放入第一象限，
     * 棋盘的最左下角是(0,0)位置 那么整个棋盘就是横坐标上9条线、
     * 纵坐标上10条线的区域 给你三个 参数 x，y，k
     * 返回“马”从(0,0)位置出发，必须走k步 最后落在(x,y)上的方法数有多少种?
     */

    public static int horseJump(int x, int y, int k) {
        if (x < 0 || x > 8 || y < 0 || y > 9 || k < 0) {
            return 0;
        }
        return process1(x, y, k, 0, 0);
    }

    public static int process1(int x, int y, int rest, int curX, int curY) {
        if (curX < 0 || curX > 8 || curY < 0 || curY > 9) {
            return 0;
        }
        if (rest == 0) {
            return curX == x && curY == y ? 1 : 0;
        }
        int res = process1(x, y, rest - 1, curX + 2, curY - 1);
        res += process1(x, y, rest - 1, curX + 1, curY - 2);
        res += process1(x, y, rest - 1, curX - 1, curY - 2);
        res += process1(x, y, rest - 1, curX - 2, curY - 1);
        res += process1(x, y, rest - 1, curX - 2, curY + 1);
        res += process1(x, y, rest - 1, curX - 1, curY + 2);
        res += process1(x, y, rest - 1, curX + 1, curY + 2);
        res += process1(x, y, rest - 1, curX + 2, curY + 1);
        return res;
    }

    // 动态规划解法
    public static int dp(int x, int y, int k) {
        int[][][] dp = new int[k + 1][9][10];
        dp[0][x][y] = 1;
        for (int rest = 1; rest <= k; rest++) {
            for (int curX = 0; curX < 9; curX++) {
                for (int curY = 0; curY < 10; curY++) {
                    int res = pick(dp, rest - 1, curX + 2, curY - 1);
                    res += pick(dp, rest - 1, curX + 1, curY - 2);
                    res += pick(dp, rest - 1, curX - 1, curY - 2);
                    res += pick(dp, rest - 1, curX - 2, curY - 1);
                    res += pick(dp, rest - 1, curX - 2, curY + 1);
                    res += pick(dp, rest - 1, curX - 1, curY + 2);
                    res += pick(dp, rest - 1, curX + 1, curY + 2);
                    res += pick(dp, rest - 1, curX + 2, curY + 1);
                    dp[rest][curX][curY] = res;
                }
            }
        }
        return dp[k][0][0];
    }

    public static int pick(int[][][] dp, int rest, int curX, int curY) {
        if (curX < 0 || curX > 8 || curY < 0 || curY > 9) {
            return 0;
        }
        return dp[rest][curX][curY];
    }


    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(horseJump(x, y, step));
        System.out.println(dp(x, y, step));
    }
}
