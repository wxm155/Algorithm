package code.dynamicProgramming;

import java.util.Arrays;

/**
 * @author wxm
 * @created 2022/11/26
 */
public class Drive {

    /**
     * 现有司机N*2人，调度中心会将所有司机平分给A、B两区域，
     * i号司机去A可得收入为income[i][0]，去B可得收入为income[i][1]
     * 返回能使所有司机总收入最高的方案是多少钱?
     * <p>
     * 总结：本题抽象为从左往右的要或不要的尝试模型，添加了平分的参数限制
     */

    public static int maxMoney(int[][] income) {
        if (income == null || income.length < 2) {
            return 0;
        }
        int len = income.length;
        int driverNum = len >> 1;
        return process1(income, 0, driverNum);
    }

    /**
     * @param income 收入数组
     * @param index  所有的司机
     * @param rest   往A区域分配的司机
     * @return 最高收入
     */
    public static int process1(int[][] income, int index, int rest) {
        if (income.length == index) {
            return 0;
        }
        // 剩下的全是A区域的司机
        if (income.length - index == rest) {
            return income[index][0] + process1(income, index + 1, rest - 1);
        }
        // 剩下全是B区域的司机
        if (rest == 0) {
            return income[index][1] + process1(income, index + 1, rest);
        }
        // 当前司机去A
        int p1 = income[index][0] + process1(income, index + 1, rest - 1);
        // 当前司机去B
        int p2 = income[index][1] + process1(income, index + 1, rest);
        return Math.max(p1, p2);
    }

    /**
     * 动态规划解法
     *
     * @param income 收入数组
     * @return 最高收入
     */
    public static int maxMoney2(int[][] income) {
        if (income == null || income.length < 2) {
            return 0;
        }
        int len = income.length;
        int driverNum = len >> 1;
        int[][] dp = new int[len + 1][driverNum + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= driverNum; j++) {
                if (len - i == j) {
                    dp[i][j] = income[i][0] + dp[i + 1][j - 1];
                } else if (j == 0) {
                    dp[i][j] = income[i][1] + dp[i + 1][j];
                } else {
                    int p1 = income[i][0] + dp[i + 1][j - 1];
                    int p2 = income[i][1] + dp[i + 1][j];
                    dp[i][j] = Math.max(p1, p2);
                }
            }
        }
        return dp[0][driverNum];
    }

    /**
     * 贪心解法
     * 假设一共有10个司机，先让所有司机去A，得到一个总收益
     * 然后看看哪5个司机改换门庭(去B)，可以获得最大的额外收益
     *
     * @param income 收入数组
     * @return 收入最大值
     */
    public static int maxMoney3(int[][] income) {
        if (income == null || income.length < 2) {
            return 0;
        }
        int len = income.length;
        int driverNum = len >> 1;
        int[] arr = new int[len];
        int sum = 0;
        for (int i = 0; i < len; i++) {
            arr[i] = income[i][1] - income[i][0];
            sum += income[i][0];
        }
        Arrays.sort(arr);
        for (int i = len - 1; i >= driverNum; i--) {
            sum += arr[i];
        }
        return sum;
    }

    /**
     * 返回随机len*2大小的正数矩阵
     * 值在0~value-1之间
     *
     * @param len   随机矩阵长度
     * @param value 随机矩阵值大小
     * @return 随机矩阵
     */
    public static int[][] randomMatrix(int len, int value) {
        int[][] ans = new int[len << 1][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = (int) (Math.random() * value);
            ans[i][1] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int value = 100;
        int testTime = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[][] matrix = randomMatrix(len, value);
            int ans1 = maxMoney(matrix);
            int ans2 = maxMoney2(matrix);
            int ans3 = maxMoney3(matrix);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("fuck....");
            }
        }
        System.out.println("测试结束");
    }

}
