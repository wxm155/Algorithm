package code.slidingwindow;

import java.util.LinkedList;

/**
 * @author wxm
 * @created 2022/9/7
 */
public class GasStation {

    /**
     * 在一条环路上有 n个加油站，其中第 i个加油站有汽油gas[i] 升。
     * 你有一辆油箱容量无限的的汽车，从第i个加油站开往第i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
     * 给定两个整数数组gas和cost，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则保证它是唯一的。
     *
     * 示例 1:
     * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
     * 输出: 3
     * 解释:
     * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
     * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
     * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
     * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
     * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
     * 因此，3 可为起始索引。
     *
     * 示例 2:
     * 输入: gas = [2,3,4], cost = [3,4,3]
     * 输出: -1
     * 解释:
     * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
     * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
     * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
     * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
     * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
     * 因此，无论怎样，你都不可能绕环路行驶一周。
     *
     * 力扣：https://leetcode.cn/problems/gas-station/
     */

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0) {
            return -1;
        }
        boolean[] res = goodArray(gas, cost);
        for (int i = 0; i < res.length; i++) {
            if (res[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] goodArray(int[] gas, int[] cost) {
        int len = gas.length;
        int newLen = len << 1;
        int[] newArr = new int[newLen];
        for (int i = 0; i < len; i++) {
            // gas  [2,2,1,4]
            // cost [3,2,1,3]
            // =>   [-1,0,0,1] 循环遍历累加和不等于0的为满足条件的结果
            int costGas = gas[i] - cost[i];
            newArr[i] = costGas;
            newArr[i + len] = costGas;
        }
        // 数组扩充一倍，依次为前缀和，方便窗口滑动
        for (int i = 1; i < newLen; i++) {
            newArr[i] += newArr[i - 1];
        }
        LinkedList<Integer> list = new LinkedList<>();
        // 排列出第一个窗口内最小累加和
        for (int i = 0; i < len; i++) {
            while (!list.isEmpty() && newArr[list.peekLast()] >= newArr[i]) {
                list.pollLast();
            }
            list.addLast(i);
        }
        boolean[] res = new boolean[len];
        for (int offset = 0, i = 0, j = len; j < newLen; offset = newArr[i++], j++) {
            // newArr减去窗口前一个值为窗口内的前缀和
            // 窗口内的前缀和最小值满足大于等于0则当前位置满足条件
            if (newArr[list.peekFirst()] - offset >= 0) {
                res[i] = true;
            }
            if (list.peekFirst() == i) {
                list.pollFirst();
            }
            while (!list.isEmpty() && newArr[list.peekLast()] >= newArr[j]) {
                list.pollLast();
            }
            list.addLast(j);
        }
        return res;
    }
}
