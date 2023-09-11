package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/09/11
 */
public class FindNumInSortedMatrix {

    /**
     * 给定一个每一行有序、每一列也有序，整体可能无序的二维数组，再给定一个数num，返回二维数组中有没有num这个数
     */

    // 利用行列的单调性，从右上角开始比对，大于则往左，小于则往下
    // 时间复杂度：O(m + n)
    public static boolean findNum(int[][] matrix, int num) {
        int N = matrix.length - 1, M = matrix[0].length - 1;
        int row = 0, col = M;
        while (row <= N && col >= 0) {
            int cur = matrix[row][col];
            if (cur == num) {
                return true;
            } else if (cur > num) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 2, 3, 4, 5, 6}, // 0
                {10, 12, 13, 15, 16, 17, 18}, // 1
                {23, 24, 25, 26, 27, 28, 29}, // 2
                {44, 45, 46, 47, 48, 49, 50}, // 3
                {65, 66, 67, 68, 69, 70, 71}, // 4
                {96, 97, 98, 99, 100, 111, 122}, // 5
                {166, 176, 186, 187, 190, 195, 200}, // 6
                {233, 243, 321, 341, 356, 370, 380} // 7
        };
        int K = 233;
        System.out.println(findNum(matrix, K));
    }
}
