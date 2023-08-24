package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/08/21
 */
public class FirstMissingPositive {

    /**
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     * <p>
     * 示例 1：
     * 输入：nums = [1,2,0]
     * 输出：3
     * <p>
     * 示例 2：
     * 输入：nums = [3,4,-1,1]
     * 输出：2
     * <p>
     * 示例 3：
     * 输入：nums = [7,8,9,11,12]
     * 输出：1
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 5 * 10^5
     * -2^31 <= nums[i] <= 2^31 - 1
     * <p>
     * 力扣：https://leetcode.cn/problems/first-missing-positive/
     * <p>
     * 扩展：字节面试题，在此题的基础上加了最小正数>=K限制条件
     * 将数组中的所有数减去一个K即当前题目的解
     */

    public static int firstMissingPositive(int[] nums) {
        // L向右扩，能扩出的位置+1就是缺失的最小正数
        // R向右的范围为无效的数
        int L = 0, R = nums.length;
        while (L != R) {
            // L扩出的数满足nums[i] = i + 1
            if (nums[L] == L + 1) {
                L++;
                // nums[L] <= L: 小于0或已经出现的数
                // nums[L] > R : 预期在L...R中找出满足的数，大于R则不满足
                // nums[nums[L] - 1] == nums[L] : L < nums[L] <= R并且在nums[L] - 1位置上的数等于L位置上的数，
                // 即在nums[L] - 1位置上的数可以满足后续步骤，L位置上的数进入垃圾区
                // 例：    L         R
                // 1,2,3,4] 7,6,7,5 [-5,20
                // 4位置上的7和6位置上的7，在后续过程中6位置上的7可以满足后续L继续扩的条件,4位置上的7进入垃圾区
            } else if (nums[L] <= L || nums[L] > R || nums[nums[L] - 1] == nums[L]) {
                swap(nums, L, --R);
            } else {
                // 例：    L         R
                // 1,2,3,4] 7,6,5,8 [-5,20
                // 4位置上的7和6位置上的5不相等，交换
                swap(nums, L, nums[L] - 1);
            }
        }
        return L + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, -1, 1};
        System.out.println(firstMissingPositive(arr));
    }
}
