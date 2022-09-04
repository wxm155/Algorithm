package code.dynamicProgramming;

/**
 * @author wxm
 * @created 2022/9/3
 */
public class NQueens {

    /**
     * N皇后问题是指在N*N的棋盘上要摆N个皇后，
     * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
     * 给定一个整数n，返回n皇后的摆法有多少种。
     * n=1，返回1 n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
     * n=8，返回92
     */

    public static int queues(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process(record,0,n);
    }

    // int[] record  record[x] = y => 对应x行上面的皇后放在第y列
    // 转化为每一行放皇后位置满足条件的方法数
    public static int process(int[] record, int row, int col) {
        // 前面所有的皇后都满足条件
        if (row == col) {
            return 1;
        }
        int ans = 0;
        // 第i个皇后放在第几列
        for (int i = 0; i < col; i++) {
            if (isValid(record, row, i)) {
                record[row] = i;
                ans += process(record, row + 1, col);
            }
        }
        return ans;

    }

    public static boolean isValid(int[] record, int row, int col) {
        // 0...row - 1行
        for (int i = 0; i < row; i++) {
            // col == record[i] 共列
            // Math.abs(record[i] - col) == Math.abs(i - row) => (a,b),(c,d)   |a-c| == |b-d| 共斜线
            if (col == record[i] || Math.abs(record[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    // 只支持1-32皇后问题
    public static int queue2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // 如7皇后问题
    // limit        0...0 1 1 1 1 1 1 1
    // colLim       0...0 0 0 1 0 0 0 0   皇后放的位置
    // leftDiaLim   0...0 0 1 0 0 0 0 0   左下角的限制
    // rightDiaLim  0...0 0 0 0 1 0 0 0   右下角的限制
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        // limit  0...0 1 1 1 1 1 1 1
        // colLim 0...0 1 1 1 1 1 1 1
        if (limit == colLim) {
            return 1;
        }
        // pos所有1的位置可以尝试皇后的位置
        // colLim | leftDiaLim | rightDiaLim  => 0...0 0 0 1 1 1 0 0 0
        // 取反 => 1...1 1 1 0 0 0 1 1 1
        // pos => 0... 0 1 1 0 0 0 1 1
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int ans = 0;
        while (pos != 0) {
            // ~pos + 1 => 1...1 0 0 1 1 1 0 0 + 0...0 0 0 0 0 0 0 1 =  1...1 0 0 1 1 1 0 1
            // mostRightOne => 0...0 0 0 0 0 0 0 1
            // 提取最右边的1
            mostRightOne = pos & (~pos + 1);
            // 0...0 1 1 0 0 0 1 0
            pos = pos - mostRightOne;
            // colim =>                                0...0 0 0 1 0 0 0 1
            // leftDiaLim = 0...0 0 1 0 0 0 0 0 =>     0...0 1 0 0 0 0 1 0
            // rightDiaLim = 0...0 0 0 0 1 0 0 0 =>    0...0 0 0 0 0 1 0 0
            ans += process2(limit, colLim | mostRightOne,
                    (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        int n = 32;

        long start = System.currentTimeMillis();
        System.out.println(queues(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(queue2(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");
    }
}
