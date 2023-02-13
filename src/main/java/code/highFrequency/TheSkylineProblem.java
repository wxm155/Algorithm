package code.highFrequency;

import java.util.*;

/**
 * @author: wxm
 * @created: 2023/02/10
 */
public class TheSkylineProblem {

    /**
     * 城市的 天际线 是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回 由这些建筑物形成的 天际线 。
     * 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
     * lefti 是第 i 座建筑物左边缘的 x 坐标。
     * righti 是第 i 座建筑物右边缘的 x 坐标。
     * heighti 是第 i 座建筑物的高度。
     * 你可以假设所有的建筑都是完美的长方形，在高度为 0 的绝对平坦的表面上。
     * 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。
     * 列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
     * 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；
     * 三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
     *
     * 示例 1：
     * 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
     * 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
     * 解释：
     *
     * 示例 2：
     * 输入：buildings = [[0,2,3],[2,5,3]]
     * 输出：[[0,3],[5,0]]
     *
     * 提示：
     * 1 <= buildings.length <= 104
     * 0 <= lefti < righti <= 231 - 1
     * 1 <= heighti <= 231 - 1
     * buildings 按 lefti 非递减排序
     *
     * 力扣：https://leetcode.cn/problems/the-skyline-problem/
     */

    public static List<List<Integer>> getSkyline(int[][] buildings) {

        int len = buildings.length;
        Node[] nodeArr = new Node[len << 1];
        // 构建node数组，每一栋楼分为开始上升高度和下降高度
        for (int i = 0; i < len; i++) {
            nodeArr[i * 2] = new Node(buildings[i][0], true, buildings[i][2]);
            nodeArr[i * 2 + 1] = new Node(buildings[i][1], false, buildings[i][2]);
        }
        // 根据x坐标位置排序
        Arrays.sort(nodeArr, new NodeComparator());
        TreeMap<Integer, Integer> heightTimes = new TreeMap<>();
        TreeMap<Integer, Integer> xHeight = new TreeMap<>();
        for (int i = 0; i < nodeArr.length; i++) {
            // 获取x轴每个位置的最高点
            if (nodeArr[i].isAdd) {
                if (!heightTimes.containsKey(nodeArr[i].height)) {
                    heightTimes.put(nodeArr[i].height, 1);
                } else {
                    heightTimes.put(nodeArr[i].height, heightTimes.get(nodeArr[i].height) + 1);
                }
            } else {
                if (heightTimes.get(nodeArr[i].height) == 1) {
                    heightTimes.remove(nodeArr[i].height);
                } else {
                    heightTimes.put(nodeArr[i].height, heightTimes.get(nodeArr[i].height) - 1);
                }
            }
            if (heightTimes.isEmpty()) {
                xHeight.put(nodeArr[i].x, 0);
            } else {
                xHeight.put(nodeArr[i].x, heightTimes.lastKey());
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : xHeight.entrySet()) {
            int curX = entry.getKey();
            int curHeight = entry.getValue();
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != curHeight) {
                res.add(Arrays.asList(curX, curHeight));
            }
        }
        return res;
    }


    private static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.x - o2.x;
        }
    }

    private static class Node {
        // x轴坐标
        public int x;
        // 高度是否是上升
        public boolean isAdd;
        // 高度
        public int height;

        public Node(int x, boolean isAdd, int height) {
            this.x = x;
            this.isAdd = isAdd;
            this.height = height;
        }
    }
}
