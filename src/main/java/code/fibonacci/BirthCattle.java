package code.fibonacci;

/**
 * @author wxm
 * @created 2022/9/17
 */
public class BirthCattle {

    /**
     * 第一年农场有1只成熟的母牛A，往后的每年：
     * 1、每一只成熟的母牛都会生一只母牛
     * 2、每一只新出生的母牛都在出生的第三年成熟
     * 3、每一只母牛永远不会死 返回N年后牛的数量
     */

    // 1 2 3 4 6 9 ...
    // => f(n) = f(n - 1) + f(n - 3) 三年前的牛可以生小牛
    public static int birthCattle(int n){
        if (n < 0){
            return 0;
        }
        if (n == 1 || n == 2 || n == 3){
            return n;
        }
        int[][] base = {
                { 1, 1, 0 },
                { 0, 0, 1 },
                { 1, 0, 0 } };
        int[][] res = matrixPower(base, n - 3);
        //           |1 1 0|
        // |3 2 1| * |0 0 1|
        //           |1 0 0|
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
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
        int n = 19;
        System.out.println(birthCattle(n));
    }
}
