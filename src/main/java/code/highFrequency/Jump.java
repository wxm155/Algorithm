package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/07/20
 */
public class Jump {

    /**
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
     * 每个元素 nums[i] 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
     * 0 <= j <= nums[i]
     * i + j < n
     * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
     * <p>
     * 示例 1:
     * 输入: nums = [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * <p>
     * 示例 2:
     * 输入: nums = [2,3,0,1,4]
     * 输出: 2
     * <p>
     * 提示:
     * <p>
     * 1 <= nums.length <= 10^4
     * 0 <= nums[i] <= 1000
     * 题目保证可以到达 nums[n-1]
     * <p>
     * 力扣：https://leetcode.cn/problems/jump-game-ii/
     */

    public int jump(int[] nums) {
        // 当前能跳到右边的最大距离位置边界下标
        int right = 0;
        // 能跳到right之前能更新的最大距离位置下标
        int maxRight = 0;
        // 跳的步数
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 向右扩能跳出距离的下标
            maxRight = Math.max(maxRight, nums[i] + i);
            // 跳到了扩出的最大位置，不得不再跳一次，并更新能跳出最大距离的位置下标
            if (i == right) {
                right = maxRight;
                steps++;
            }
        }
        return steps;
    }
}
