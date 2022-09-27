package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2022/09/27
 */
public class RemoveBoxes {

    /**
     * 给出一些不同颜色的盒子 boxes ，盒子的颜色由不同的正数表示。
     * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），
     * 这样一轮之后你将得到 k * k 个积分。返回 你能获得的最大积分和 。
     *
     * 示例 1：
     * 输入：boxes = [1,3,2,2,2,3,4,3,1]
     * 输出：23
     * 解释：
     * [1, 3, 2, 2, 2, 3, 4, 3, 1]
     * ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
     * ----> [1, 3, 3, 3, 1] (1*1=1 分)
     * ----> [1, 1] (3*3=9 分)
     * ----> [] (2*2=4 分)
     *
     * 示例 2：
     * 输入：boxes = [1,1,1]
     * 输出：9
     *
     * 示例 3：
     * 输入：boxes = [1]
     * 输出：1
     *
     * 力扣：https://leetcode.cn/problems/remove-boxes/
     *
     */

    // 暴力解
    public int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0){
            return 0;
        }
        return process(boxes,0,boxes.length - 1,0);
    }

    // arr[L...R]上消除，L左边有num个arr[L]的数
    // 返回最大消除得分
    public static int process(int[] arr, int L, int R, int num) {
        if (L > R) {
            return 0;
        }
        // 连着前面的一起消
        int res = process(arr, L + 1, R, 0) + (num + 1) * (num + 1);
        // 前面的num个X，和arr[L]数，合在一起了，现在有num+1个arr[L]位置的数
        for (int i = L + 1; i <= R; i++) {
            if (arr[i] == arr[L]) {
                res = Math.max(res, process(arr, L + 1, i - 1, 0) + process(arr, i, R, num + 1));
            }
        }
        return res;
    }

    // 暴力解+缓存
    public int removeBoxes1(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }
        int len = boxes.length;
        int[][][] dp = new int[len][len][len];
        return process1(boxes, 0, len - 1, 0, dp);
    }

    public static int process1(int[] arr, int L, int R, int num, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (dp[L][R][num] > 0) {
            return dp[L][R][num];
        }
        // 连着前面的一起消
        int res = process1(arr, L + 1, R, 0,dp) + (num + 1) * (num + 1);
        // 前面的num个X，和arr[L]数，合在一起了，现在有num+1个arr[L]位置的数
        for (int i = L + 1; i <= R; i++) {
            if (arr[i] == arr[L]) {
                res = Math.max(res, process1(arr, L + 1, i - 1, 0,dp) + process1(arr, i, R, num + 1,dp));
            }
        }
        dp[L][R][num] = res;
        return res;
    }

    // 最优解，常数时间的优化
    public int removeBoxes2(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }
        int len = boxes.length;
        int[][][] dp = new int[len][len][len];
        return process2(boxes, 0, len - 1, 0, dp);
    }

    public static int process2(int[] arr, int L, int R, int num, int[][][] dp) {
        if (L > R) {
            return 0;
        }
        if (dp[L][R][num] > 0) {
            return dp[L][R][num];
        }
        int last = L;
        // 将前面num个x与arr[L...R]中从L开始的相同的x一起消除
        while (last + 1 <= R && arr[last + 1] == arr[L]) {
            last++;
        }
        int pre = num + last - L;
        int res = process2(arr, last + 1, R, 0, dp) + (pre + 1) * (pre + 1);
        for (int i = last + 2; i <= R; i++) {
            // arr[i] == arr[L] 并且是首个arr[L]
            if (arr[i] == arr[L] && arr[i - 1] != arr[L]) {
                res = Math.max(res, process2(arr, last + 1, i - 1, 0, dp) + process2(arr, i, R, pre + 1, dp));
            }
        }
        dp[L][R][num] = res;
        return res;
    }
}
