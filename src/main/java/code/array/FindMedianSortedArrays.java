package code.array;

/**
 * @author: wxm
 * @created: 2022/03/15
 */
public class FindMedianSortedArrays {

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 算法的时间复杂度应该为 O(log (m+n)) 。。
     * <p>
     * 示例 1：
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     * <p>
     * 示例 2：
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     * <p>
     * 力扣：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
     *
     * 思路: 对于有序数组的合并，借鉴归并排序的做法
     */

    /**
     * 解法一：合并数组，时间复杂度为O(m + n),额外空间复杂度为O(m + n)
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] temp = new int[nums1.length + nums2.length];

        int index = 0;
        int p1 = 0;
        int p2 = 0;

        while (p1 <= (nums1.length - 1) && p2 <= (nums2.length - 1)) {
            temp[index++] = nums1[p1] < nums2[p2] ? nums1[p1++] : nums2[p2++];
        }
        while (p1 <= (nums1.length - 1)) {
            temp[index++] = nums1[p1++];
        }
        while (p2 <= (nums2.length - 1)) {
            temp[index++] = nums2[p2++];
        }

        double result = (temp.length & 1) == 0
                ? (((double) temp[(temp.length - 1) >> 1] + (double) temp[((temp.length - 1) >> 1) + 1]) / 2)
                : temp[(temp.length - 1) >> 1];
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }
}
