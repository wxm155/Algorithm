package code.leetCodeTop100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxm
 * @created 2024/2/1
 */
public class SpiralOrder {

    /**
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * <p>
     * 示例 1：
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,2,3,6,9,8,7,4,5]
     * <p>
     * 示例 2：
     * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
     * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
     * <p>
     * 提示：
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 10
     * -100 <= matrix[i][j] <= 100
     * <p>
     * 力扣：https://leetcode.cn/problems/spiral-matrix/description/?envType=study-plan-v2&envId=top-100-liked
     */

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;

        while (true) {
            for (int i = l; i <= r; i++) {
                res.add(matrix[t][i]);
            }
            if (++t > b) {
                break;
            }
            for (int i = t; i <= b; i++) {
                res.add(matrix[i][r]);
            }
            if (--r > l) {
                break;
            }
            for (int i = r; i >= l; i--) {
                res.add(matrix[b][i]);
            }
            if (--b < t) {
                break;
            }
            for (int i = b; i >= t; i--) {
                res.add(matrix[i][l]);
            }
            if (++l > r) {
                break;
            }
        }
        return res;
    }
}
