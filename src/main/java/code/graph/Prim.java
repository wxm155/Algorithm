package code.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author wxm
 * @created 2022/7/23
 */
public class Prim {

    /**
     * 图的最小生成树之Prim算法
     * 由点解锁相连边
     */

    public static Set<Graph.Edge> primMST(Graph graph){

        // 根据边从小到大排序
        PriorityQueue<Graph.Edge> queue = new PriorityQueue<>(new EdgeComparator());
        // 已经解锁的点
        Set<Graph.Node> nodeSet = new HashSet<>();
        // 结果
        Set<Graph.Edge> res = new HashSet<>();

        // 随意挑选一个点
        for (Graph.Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)){
                nodeSet.add(node);
                // 由一个点解锁所有相连的边
                for (Graph.Edge edge : node.edges) {
                    queue.add(edge);
                }
                while (!queue.isEmpty()){
                    // 弹出解锁边中最小的边
                    Graph.Edge edge = queue.poll();
                    // 解锁下一个节点
                    Graph.Node toNode = edge.to;
                    if (!nodeSet.contains(toNode)){
                        res.add(edge);
                        nodeSet.add(toNode);
                        // 再解锁所有相连的边
                        for (Graph.Edge toEdge : toNode.edges) {
                            queue.add(toEdge);
                        }
                    }
                }
                // 一次遍历解锁所有点
                // 如果为一张连续的图，可以使用break，
                // 如果为不连续的图，不能使用break
                break;
            }
        }
        return res;
    }


    public static class EdgeComparator implements Comparator<Graph.Edge> {
        @Override
        public int compare(Graph.Edge o1, Graph.Edge o2) {
            return o1.weight - o2.weight;
        }
    }
}
