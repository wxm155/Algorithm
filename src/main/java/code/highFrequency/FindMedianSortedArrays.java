package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/08/10
 */
public class FindMedianSortedArrays {

    /**
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * 算法的时间复杂度应该为 O(log (m+n)) 。
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
     * 提示：
     * nums1.length == m
     * nums2.length == n
     * 0 <= m <= 1000
     * 0 <= n <= 1000
     * 1 <= m + n <= 2000
     * -10^6 <= nums1[i], nums2[i] <= 10^6
     * <p>
     * 力扣：https://leetcode.cn/problems/median-of-two-sorted-arrays/description/
     * <p>
     * 扩展题目：
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数
     * 进阶：在两个都有序的数组中找整体第K小的数，可以做到O(log(Min(M,N)))
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2;
            } else {
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
        } else if (nums1.length != 0) {
            if (even) {
                return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
            } else {
                return nums1[size / 2];
            }
        } else if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0.0;
        }
    }

    // 查找两个正序数组的第K小的数
    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
        int[] longs = arr1.length > arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length > arr2.length ? arr2 : arr1;
        int l = longs.length, s = shorts.length;
        // 1、k <= s
        // 第K小的数一定在longs[0...kth - 1]或shorts[0...kth - 1]中
        if (kth <= s) {
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        // 2、k > l
        if (kth > l) {
            // shorts[kth - l - 1] >= longs[l - 1] 即 shorts[kth - l - 1]为第k小的数
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            // longs[kth - l - 1] >= shorts[s - 1] 即 longs[kth - l - 1]为第k小的数
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            // s l k
            // 5 8 9
            return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
        }
        // 3、s < k <= l
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        // 2 5 10
        return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
    }

    // A.length = B.length
    // 获取两个数组的上中位数
    // 数字仅代表次序
    // 奇数例子：
    // A: 1  2  3  4  5  6  7
    // B: 1` 2` 3` 4` 5` 6` 7`
    // 偶数例子：
    // A: 1  2  3  4  5  6
    // B: 1` 2` 3` 4` 5` 6`
    // 核心思想：二分法
    public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
        int midA, midB;
        while (s1 < e1) {
            midA = (s1 + e1) / 2;
            midB = (s2 + e2) / 2;
            if (A[midA] == B[midB]) {
                return A[midA];
            }
            // 数组长度为奇数
            if (((e1 - s1 + 1) & 1) == 1) {
                // 4 > 4` 数组变为
                // A: 1  2  3
                // B: 5` 6` 7`
                // 其余位置一定不是中位数
                if (A[midA] > B[midB]) {
                    if (B[midB] >= A[midA - 1]) {
                        return B[midB];
                    }
                    e1 = midA - 1;
                    s2 = midB + 1;
                } else {
                    // 4 < 4` 数组变为
                    // A: 5  6  7
                    // B: 1` 2` 3`
                    if (A[midA] >= B[midB - 1]) {
                        return A[midA];
                    }
                    e2 = midB - 1;
                    s1 = midA + 1;
                }
                // 数组长度为偶数
            } else {
                // 数组变为：
                // A: 1  2  3
                // B: 4` 5` 6`
                if (A[midA] > B[midB]) {
                    e1 = midA;
                    s2 = midB + 1;
                } else {
                    // 数组变为：
                    // A: 4  5  6
                    // B: 1` 2` 3`
                    e2 = midB;
                    s1 = midA + 1;
                }
            }
        }
        return Math.min(A[s1], B[s2]);
    }
}
