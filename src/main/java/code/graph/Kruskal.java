package code.graph;

import java.util.*;

/**
 * @author: wxm
 * @created: 2022/07/20
 */
public class Kruskal {

    /**
     * 图的最小生成树之kruskal算法
     * 由边解锁点
     */


    /**
     * 根据图的边的权重依次从小到大获取，形成环则跳过
     * @param graph
     * @return
     */
    public static Set<Graph.Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.markSet(graph.nodes.values());
        // 小根堆
        PriorityQueue<Graph.Edge> queue = new PriorityQueue<>(new EdgeComparator());
        for (Graph.Edge edge : graph.edges) {
            queue.offer(edge);
        }
        Set<Graph.Edge> res = new HashSet<>();
        while (!queue.isEmpty()){
            Graph.Edge edge = queue.poll();
            if (!unionFind.isSameSet(edge.from,edge.to)){
                res.add(edge);
                // 合并
                unionFind.union(edge.from,edge.to);
            }
        }
        return res;
    }


    public static class EdgeComparator implements Comparator<Graph.Edge>{

        @Override
        public int compare(Graph.Edge o1, Graph.Edge o2) {
            return o1.weight - o2.weight;
        }
    }



    // 并查集
    public static class UnionFind{
        public Map<Graph.Node, Graph.Node> parent;
        public Map<Graph.Node,Integer> size;

        public UnionFind(){
            parent = new HashMap<>();
            size = new HashMap<>();
        }

        public void markSet(Collection<Graph.Node> nodes){
            for (Graph.Node node : nodes) {
                parent.put(node,node);
                size.put(node,1);
            }
        }

        public Graph.Node findFather(Graph.Node node){
            Stack<Graph.Node> stack = new Stack<>();
            while (node != parent.get(node)){
                stack.push(node);
                node = parent.get(node);
            }
            // 路径压缩
            while (!stack.isEmpty()){
                parent.put(stack.pop(),node);
            }
            return node;
        }

        public void union(Graph.Node node1, Graph.Node node2){
            if (node1 == null || node2 == null){
                return;
            }
            Graph.Node f1 = findFather(node1);
            Graph.Node f2 = findFather(node2);
            if (f1 != f2){
                int s1 = size.get(f1);
                int s2 = size.get(f2);
                if (s1 > s2){
                    parent.put(f2,f1);
                    size.put(f1,s1 + s2);
                    size.remove(f2);
                }else {
                    parent.put(f1,f2);
                    size.put(f2,s1 + s2);
                    size.remove(f1);
                }
            }
        }

        public boolean isSameSet(Graph.Node node1, Graph.Node node2){
            return findFather(node1) == findFather(node2);
        }
    }
}
