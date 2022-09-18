package code.fibonacci;

/**
 * @author wxm
 * @created 2022/9/17
 */
public class ZeroLeftOneStringNumber {

    /**
     * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串 如果某个字符串，
     * 任何0字符的左边都有1紧挨着，认为这个字符串达标返回有多少达标的字符串
     */

    public static int getNumber1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    // 1 [0......] 接下来满足 1 [0 1......] 满足process(i + 2,n)
    // 1 [1......] process(i + 1,n)
    // 必须满足i的前一位必须是1，不然直接无法达标
    public static int process(int i, int n) {
        if (i == n - 1) {
            return 2;
        }
        if (i == n) {
            return 1;
        }
        return process(i + 1, n) + process(i + 2, n);
    }

    // n = 1 => 1
    // n = 2 => 2
    // n = 3 => 3
    // n = 4 => 5
    // ..... 满足f(n) = f(n - 1) + f(n - 2)
    public static int getNumber2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] power = matrixPower(base, n - 2);
        return 2 * power[0][0] + power[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        // 矩阵中为1的矩阵
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            res[i][i] = 1;
        }
        int[][] t = m;
        // |a,b|
        // |c,d| 的N-2次方加速
        // 例：10^75 => 10^0...01001011  => 10^1 * 10^2 * 10^8 * 10^64
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = product(res, t);
            }
            t = product(t, t);
        }
        return res;
    }

    // 矩阵相乘
    public static int[][] product(int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int c = 0; c < k; c++) {
                    ans[i][j] += a[i][c] * b[c][j];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(getNumber1(i));
            System.out.println(getNumber2(i));
            System.out.println("==============");
        }
    }
}
