package code.dynamicProgramming;

/**
 * @author: wxm
 * @created: 2023/07/12
 */
public class FindWordInMatrix {

    /**
     * 给定一个char[][] matrix，也就是char类型的二维数组，再给定一个字符串word，
     * 可以从任何一个某个位置出发，可以走上下左右，能不能找到word？
     * 比如：
     * char[][] m = {
     * { 'a', 'b', 'z' },
     * { 'c', 'd', 'o' },
     * { 'f', 'e', 'o' },
     * };
     * word = "zooe"
     * 是可以找到的
     * <p>
     * 设定1：可以走重复路的情况下，返回能不能找到
     * 比如，word = "zoooz"，是可以找到的，z -> o -> o -> o -> z，因为允许走一条路径中已经走过的字符
     * <p>
     * 设定2：不可以走重复路的情况下，返回能不能找到
     * 比如，word = "zoooz"，是不可以找到的，因为允许走一条路径中已经走过的字符不能重复走
     * <p>
     * 写出两种设定下的code
     */

    /**
     * 可以走重复的路
     *
     * @param m    给定字符
     * @param word 给定目标字符串
     * @return 返回结果
     */
    public static boolean findWord1(char[][] m, String word) {
        if (word == null || word.equals("")) {
            return true;
        }
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return false;
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (process1(m, i, j, 0, word.toCharArray())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 可以走重复的路
     * 表示从m[i][j]开始，能否找到words[k...]的后缀串
     * 可以改动态规划
     *
     * @param m     给定的二维数组
     * @param i     二维数组的列
     * @param j     二维数组的行
     * @param k     字符开始的位置
     * @param words 给定的字符
     * @return 结果
     */
    public static boolean process1(char[][] m, int i, int j, int k, char[] words) {
        // 走到最后，一定可以拼出
        if (k == words.length) {
            return true;
        }
        // 越界或者当前字符不等于要拼出的字符
        if (i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != words[k]) {
            return false;
        }
        // 上下左右去尝试
        boolean p1 = process1(m, i + 1, j, k + 1, words);
        boolean p2 = process1(m, i - 1, j, k + 1, words);
        boolean p3 = process1(m, i, j + 1, k + 1, words);
        boolean p4 = process1(m, i, j - 1, k + 1, words);
        return p1 || p2 || p3 || p4;
    }

    /**
     * 不可以走重复的路
     *
     * @param m    给定字符
     * @param word 给定目标字符串
     * @return 返回结果
     */
    public static boolean findWord2(char[][] m, String word) {
        if (word == null || word.equals("")) {
            return true;
        }
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return false;
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (process2(m, i, j, 0, word.toCharArray())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 不可以走重复的路
     * 表示从m[i][j]开始，能否找到words[k...]的后缀串
     * 不能改动态规划，m中的字符在变化
     *
     * @param m     给定的二维数组
     * @param i     二维数组的列
     * @param j     二维数组的行
     * @param k     字符开始的位置
     * @param words 给定的字符
     * @return 结果
     */
    public static boolean process2(char[][] m, int i, int j, int k, char[] words) {
        // 走到最后，一定可以拼出
        if (k == words.length) {
            return true;
        }
        // 越界或者当前字符不等于要拼出的字符
        if (i == -1 || i == m.length || j == -1 || j == m[0].length || m[i][j] != words[k]) {
            return false;
        }
        // m[i][j]走过的路替换成0字符，防止走过的路重复走
        // m[i][j] != words[k]会直接返回
        m[i][j] = 0;
        // 上下左右去尝试
        boolean p1 = process2(m, i + 1, j, k + 1, words);
        boolean p2 = process2(m, i - 1, j, k + 1, words);
        boolean p3 = process2(m, i, j + 1, k + 1, words);
        boolean p4 = process2(m, i, j - 1, k + 1, words);
        // 深度优先遍历恢复现场
        m[i][j] = words[k];
        return p1 || p2 || p3 || p4;
    }
}
