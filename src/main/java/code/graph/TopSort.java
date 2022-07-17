package code.graph;

import java.util.*;

/**
 * @author wxm
 * @created 2022/7/17
 */
public class TopSort {

    /**
     * 给定一个有向图，图节点的拓扑排序定义如下:
     * 对于图中的每一条有向边 A -> B , 在拓扑排序中A一定在B之前.
     * 拓扑排序中的第一个节点可以是图中的任何一个没有其他节点指向它的节点.
     * 针对给定的有向图找到任意一种拓扑排序的顺序.
     *
     * 连接：https://www.lintcode.com/problem/topological-sorting
     *
     * 拓扑排序一般用于编译时循环依赖检查，spring中的依赖就采用拓扑排序
     */

    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // 保存每一个节点有多少个节点指向它
        Map<DirectedGraphNode,Integer> innerMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            innerMap.put(node,0);
        }
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode next : node.neighbors) {
                innerMap.put(next,innerMap.get(next) + 1);
            }
        }
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : innerMap.keySet()) {
            if (innerMap.get(node) == 0){
                queue.add(node);
            }
        }
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        while(!queue.isEmpty()){
            DirectedGraphNode node = queue.poll();
            res.add(node);
            for (DirectedGraphNode neighbor : node.neighbors) {
                innerMap.put(neighbor,innerMap.get(neighbor) - 1);
                if (innerMap.get(neighbor) == 0){
                    queue.offer(neighbor);
                }
            }
        }
        return res;
    }
}
