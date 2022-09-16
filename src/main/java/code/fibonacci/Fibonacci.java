package code.fibonacci;

/**
 * @author wxm
 * @created 2022/9/16
 */
public class Fibonacci {

    /**
     * 斐波拉契数列问题：
     *
     * 求斐波拉契数列矩阵乘法的方法：
     * 1、斐波拉契数列的线性求解(O(N))的方法 F(N) = F(N - 1) + F(N - 2) (第一项是1，第二项是1)
     * 2、同时利用线性代数，也可以改写出另一种表示 |F(N),F(N-1)| = |F(2),F(1)| * 某个二阶矩阵的N-2次方
     * 3、求出这个二阶矩阵，进而最快求出这个二阶矩阵的N-2次方，时间复杂度 O(logN)
     * 满足严格递推式 F(N) = F(N - 1) + F(N - 2)都有时间复杂度为 O(logN)的求解方法
     *
     *
     * 例：
     * 斐波拉契数列：1,1,2,3,5....
     *                             |a,b|                     |a,b|
     * |f(3),f(2)| = |f(2),f(1)| * |c,d|  => |2,1| = |1,1| * |c,d|  => 1 * a + 1 * c = 2
     *                                                                 1 * b + 1 * d = 1
     *                                                                                      ==> a = 1,c = 1,b = 1,d = 0
     *                             |a,b|                    |a,b|
     * |f(4),f(3)| = |f(3),f(2)| * |c,d| => |3,2| = |2,1| * |c,d|  => 2 * a + 1 * c = 3
     *                                                                2 * b + 1 * d = 2
     *
     *                                                |a,b| ^ n-2
     * 错位相减得出结论：|f(n),f(n - 1)| = |f(2),f(1)| * |c,d|
     *
     * 推广：f(n) = af(n -1) + bf(n -i)为i阶递推式满足上诉结论,相加可以不连续
     *
     */

    // O(N)
    public static int fibonacci1(int n){
        if (n < 0){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }
        return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    // O(N)
    public static int fibonacci2(int n){
        if (n < 0){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }
        int res = 1;
        int temp = 0;
        int pre = 1;
        for (int i = 3; i <= n; i++) {
            temp = res;
            res = res + pre;
            pre = temp;
        }
        return res;
    }

    // O(logN)
    public static int fibonacci3(int n){
        if (n < 0){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }
        // |a,b|
        // |c,d|
        int[][] base = {
                    {1,1},
                    {1,0}
                    };
        int[][] res = matrixPower(base, n - 2);
        // 结论
        return res[0][0] + res[1][0];
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
        int n = 10;
        System.out.println(fibonacci1(n));
        System.out.println(fibonacci2(n));
        System.out.println(fibonacci3(n));
    }
}
