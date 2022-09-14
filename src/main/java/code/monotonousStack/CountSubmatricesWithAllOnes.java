package code.monotonousStack;

/**
 * @author wxm
 * @created 2022/9/12
 */
public class CountSubmatricesWithAllOnes {

    /**
     * 给你一个 m x n 的二进制矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
     *
     * 示例 1：
     * 输入：mat = [[1,0,1],[1,1,0],[1,1,0]]
     * 输出：13
     * 解释：
     * 有 6 个 1x1 的矩形。
     * 有 2 个 1x2 的矩形。
     * 有 3 个 2x1 的矩形。
     * 有 1 个 2x2 的矩形。
     * 有 1 个 3x1 的矩形。
     * 矩形数目总共 = 6 + 2 + 3 + 1 + 1 = 13 。
     *
     * 力扣：https://leetcode.cn/problems/count-submatrices-with-all-ones/
     */

    public static int numSubmat(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }
        int res = 0;
        int[] heights = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                heights[j] = mat[i][j] == 0 ? 0 : heights[j] + 1;
            }
            res += countFromBottom(heights);
        }
        return res;
    }

    // 例：
    //              1
    //              1
    //              1         1
    //    1         1         1
    //    1         1         1
    //    1         1         1
    //
    //    2  ....   6   ....  9
    // 如上图，假设在6位置，1的高度为6
    // 在6位置的左边，离6位置最近、且小于高度6的位置是2，2位置的高度是3
    // 在6位置的右边，离6位置最近、且小于高度6的位置是9，9位置的高度是4
    // 此时我们求什么？
    // 1) 求在3~8范围上，必须以高度6作为高的矩形，有几个？
    // 2) 求在3~8范围上，必须以高度5作为高的矩形，有几个？
    // 小于4的高度，一律不求，交由后续的4来求
    // 求必须以位置6的高度6作为高的矩形，有几个？
    // 3..3  3..4  3..5  3..6  3..7  3..8
    // 4..4  4..5  4..6  4..7  4..8
    // 5..5  5..6  5..7  5..8
    // 6..6  6..7  6..8
    // 7..7  7..8
    // 8..8
    // 1+2+3....+n => (n * (n + 1)/2)
    public static int countFromBottom(int[] heights) {
        int res = 0;
        int len = heights.length;
        int[] stack = new int[len];
        int index = -1;
        for (int i = 0; i < len; i++) {
            while (index != -1 && heights[stack[index]] >= heights[i]) {
                int cur = stack[index--];
                // 相等直接跳过等后续弹出计算
                if (heights[cur] > heights[i]) {
                    int preIndex = index == -1 ? -1 : stack[index];
                    int wide = i - 1 - preIndex;
                    // 实际计算高度为当前高度减去左右两边最高的高度
                    int down = Math.max(preIndex == -1 ? 0 : heights[preIndex], heights[i]);
                    res += (heights[cur] - down) * sum(wide);
                }
            }
            stack[++index] = i;
        }
        while (index != -1) {
            int cur = stack[index--];
            int preIndex = index == -1 ? -1 : stack[index];
            int wide = heights.length - 1 - preIndex;
            int down = preIndex == -1 ? 0 : heights[preIndex];
            res += (heights[cur] - down) * sum(wide);
        }
        return res;
    }

    // 1+2+3....+n => (n * (n + 1)/2)
    public static int sum(int n) {
        return (n * (n + 1) >> 1);
    }
}
