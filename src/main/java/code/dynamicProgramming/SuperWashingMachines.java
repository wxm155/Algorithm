package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/08/16
 */
public class SuperWashingMachines {

    /**
     * 假设有 n 台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
     * 在每一步操作中，你可以选择任意 m (1 <= m <= n) 台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
     * 给定一个整数数组 machines 代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的 最少的操作步数 。
     * 如果不能使每台洗衣机中衣物的数量相等，则返回 -1 。
     * <p>
     * 示例 1：
     * 输入：machines = [1,0,5]
     * 输出：3
     * 解释：
     * 第一步:    1     0 <-- 5    =>    1     1     4
     * 第二步:    1 <-- 1 <-- 4    =>    2     1     3
     * 第三步:    2     1 <-- 3    =>    2     2     2
     * <p>
     * 示例 2：
     * 输入：machines = [0,3,0]
     * 输出：2
     * 解释：
     * 第一步:    0 <-- 3     0    =>    1     2     0
     * 第二步:    1     2 --> 0    =>    1     1     1
     * <p>
     * 示例 3：
     * 输入：machines = [0,2,0]
     * 输出：-1
     * 解释：
     * 不可能让所有三个洗衣机同时剩下相同数量的衣物。
     * <p>
     * 提示：
     * n == machines.length
     * 1 <= n <= 10^4
     * 0 <= machines[i] <= 10^5
     * <p>
     * 力扣：https://leetcode.cn/problems/super-washing-machines/
     */

    // 核心思想：以单点的瓶颈来决定整体的瓶颈
    public int findMinMoves(int[] machines) {
        int len = machines.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += machines[i];
        }
        if (sum % len != 0) {
            return -1;
        }
        int avg = sum / len;
        int leftSum = 0;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            // i的左边满足平均分配需要的衣服数
            int leftRest = leftSum - i * avg;
            // i的右边满足平均分配需要的衣服数
            int rightRest = (sum - leftSum - machines[i]) - (len - 1 - i) * avg;
            // 1、左边和右边都小于0，则一定需要从i位置上移动衣服满足两边平均
            // 2、左边小于0，右边大于0，只需满足绝对值最大的移动次数
            // 3、左边大于0，右边小于0，只需满足绝对值最大的移动次数
            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += machines[i];
        }
        return ans;
    }
}
