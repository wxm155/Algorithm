package code.leetCodeTop100;

/**
 * @author wxm
 * @created 2024/2/1
 */
public class Rotate {

    /**
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     * <p>
     * 示例 1:
     * 输入: nums = [1,2,3,4,5,6,7], k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右轮转 1 步: [7,1,2,3,4,5,6]
     * 向右轮转 2 步: [6,7,1,2,3,4,5]
     * 向右轮转 3 步: [5,6,7,1,2,3,4]
     * <p>
     * 示例 2:
     * 输入：nums = [-1,-100,3,99], k = 2
     * 输出：[3,99,-1,-100]
     * 解释:
     * 向右轮转 1 步: [99,-1,-100,3]
     * 向右轮转 2 步: [3,99,-1,-100]
     * <p>
     * 提示：
     * 1 <= nums.length <= 10^5
     * -2^31 <= nums[i] <= 2^31 - 1
     * 0 <= k <= 10^5
     * <p>
     * 力扣：https://leetcode.cn/problems/rotate-array/?envType=study-plan-v2&envId=top-100-liked
     */

    public void rotate(int[] nums, int k) {
        int[] newArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newArr[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = newArr[i];
        }
    }
}
