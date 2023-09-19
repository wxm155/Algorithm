package code.highFrequency;

/**
 * @author: wxm
 * @created: 2023/09/11
 */
public class JosephusProblem {

    /**
     * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     *
     * 示例 1：
     * 输入: n = 5, m = 3
     * 输出: 3
     *
     * 示例 2：
     * 输入: n = 10, m = 17
     * 输出: 2
     *
     * 限制：
     * 1 <= n <= 10^5
     * 1 <= m <= 10^6
     *
     * 力扣：https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
     */

    // 约瑟夫环问题：
    // 例如41个人围城一个圈，每一个人的位置都代表一个数字。从一个位置开始报数，只要是3的倍数就要被旁边的人杀掉，
    // 如果那个位置的人被杀掉就跳过那个人的位置继续报数。请问最后活下来的人的位置是几号？
    public int lastRemaining(int n, int m) {
        int ans = 1;
        int r = 1;
        while (r <= n) {
            ans = (ans + m - 1) % (r++) + 1;
        }
        return ans - 1;
    }
}
