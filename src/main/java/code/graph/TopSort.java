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
        // 每次从没有指向自己节点开始取
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

    /**
     * 深度优先遍历解法一：节点深度越大，对应拓扑序越靠前
     * @param graph
     * @return
     */
    public static ArrayList<DirectedGraphNode> dfs(ArrayList<DirectedGraphNode> graph){
        Map<DirectedGraphNode,Record> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            process(node,map);
        }
        ArrayList<Record> tempList = new ArrayList<>(graph.size());
        for (Record record : map.values()) {
            tempList.add(record);
        }
        Collections.sort(tempList,new RecordComparator());

        ArrayList<DirectedGraphNode> res = new ArrayList<>(graph.size());
        for (Record record : tempList) {
            res.add(record.cur);
        }
        return res;
    }

    public static class RecordComparator implements Comparator<Record>{
        @Override
        public int compare(Record o1, Record o2) {
            return o2.deep - o1.deep;
        }
    }

    public static Record process(DirectedGraphNode cur,Map<DirectedGraphNode,Record> map){
        if (map.containsKey(cur)){
            return map.get(cur);
        }
        int deep = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            deep = Math.max(deep,process(next,map).deep);
        }
        // 深度加上当前节点
        Record record = new Record(cur, deep + 1);
        map.put(cur,record);
        return record;
    }


    /**
     * 辅助类：记录当前节点对应的深度
     */
    public static class Record{
        // 当前节点
        public DirectedGraphNode cur;
        // 深度
        public int deep;

        public Record(DirectedGraphNode cur,int deep){
            this.cur = cur;
            this.deep = deep;
        }
    }

    /**
     * 深度优先遍历解法二：记录节点数量，与深度类似
     * @param graph
     * @return
     */
    public static ArrayList<DirectedGraphNode> dfs2(ArrayList<DirectedGraphNode> graph){
        Map<DirectedGraphNode, Record2> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            process2(node, map);
        }
        ArrayList<Record2> tempList = new ArrayList<>(graph.size());
        for (Record2 record2 : map.values()) {
            tempList.add(record2);
        }
        Collections.sort(tempList, new Record2Comparator());

        ArrayList<DirectedGraphNode> res = new ArrayList<>(graph.size());
        for (Record2 record2 : tempList) {
            res.add(record2.cur);
        }
        return res;

    }

    public static Record2 process2(DirectedGraphNode cur, Map<DirectedGraphNode, Record2> map) {
        if (map.containsKey(cur)) {
            return map.get(cur);
        }  
        long nodes = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            nodes += process2(next, map).nodes;
        }
        Record2 record2 = new Record2(cur, nodes + 1);
        map.put(cur, record2);
        return record2;
    }

    public static class Record2Comparator implements Comparator<Record2> {
        @Override
        public int compare(Record2 o1, Record2 o2) {
            return o1.nodes == o2.nodes ? 0 : (o1.nodes > o2.nodes ? -1 : 1);
        }
    }

    public static class Record2 {
        public DirectedGraphNode cur;
        public long nodes;

        public Record2(DirectedGraphNode cur, long nodes) {
            this.cur = cur;
            this.nodes = nodes;
        }
    }
}
