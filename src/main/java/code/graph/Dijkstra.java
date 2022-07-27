package code.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author wxm
 * @created 2022/7/23
 */
public class Dijkstra {

    /**
     * 迪瑞克斯拉算法
     * 给定一个图的起始点，返回到所有点的最小距离
     */

    public static HashMap<Graph.Node, Integer> dijkstra1(Graph.Node from) {
        // 对应点和到对应点的最小距离
        HashMap<Graph.Node, Integer> distanceMap = new HashMap<>();
        Set<Graph.Node> selectedSet = new HashSet<>();
        distanceMap.put(from, 0);
        Graph.Node minNode = getMinDistanceUnSelected(distanceMap, selectedSet);
        while (minNode != null) {
            Integer distance = distanceMap.get(minNode);
            for (Graph.Edge edge : minNode.edges) {
                Graph.Node toNode = edge.to;
                if (distanceMap.containsKey(toNode)) {
                    // 当前点的距离到下一个点的距离取最小值
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                } else {
                    distanceMap.put(toNode, distance + edge.weight);
                }
                selectedSet.add(minNode);
                minNode = getMinDistanceUnSelected(distanceMap, selectedSet);
            }
        }
        return distanceMap;
    }

    /**
     * 获取最小距离并且没有并选中过的点
     *
     * @param distanceMap
     * @param selectedSet
     * @return
     */
    public static Graph.Node getMinDistanceUnSelected(HashMap<Graph.Node, Integer> distanceMap, Set<Graph.Node> selectedSet) {
        Graph.Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Graph.Node, Integer> entry : distanceMap.entrySet()) {
            Graph.Node node = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectedSet.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    /**
     * 每次弹出最小距离的节点和距离
     *
     * @param head
     * @param size
     * @return
     */
    public static HashMap<Graph.Node, Integer> dijkstra2(Graph.Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Graph.Node, Integer> res = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Graph.Node node = record.node;
            int distance = record.distance;
            for (Graph.Edge edge : node.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            res.put(node, distance);
        }
        return res;
    }


    public static class NodeRecord {
        public Graph.Node node;
        public int distance;

        public NodeRecord(Graph.Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    // 加强堆
    private static class NodeHeap {
        // 堆结构
        public Graph.Node[] nodes;
        // 节点在堆中的位置,index为nodes的下标
        public Map<Graph.Node, Integer> heapIndexMap;
        // 节点的最小距离
        public Map<Graph.Node, Integer> distanceMap;
        // 堆上有多少个点
        public int size;

        public NodeHeap(int size) {
            nodes = new Graph.Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public void addOrUpdateOrIgnore(Graph.Node node, int distance) {
            if (isHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                distanceMap.put(node, distance);
                heapIndexMap.put(node, size);
                insertHeapify(size++);
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[0], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        private void insertHeapify(int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int min = left + 1 < size && distanceMap.get(nodes[left]) < distanceMap.get(nodes[left + 1]) ? left : left + 1;
                min = distanceMap.get(nodes[min]) < distanceMap.get(nodes[index]) ? min : index;
                if (min == index) {
                    break;
                }
                swap(index, min);
                index = min;
                left = index * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            distanceMap.put(nodes[i], j);
            distanceMap.put(nodes[j], i);
            Graph.Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
        }

        // 是否已经存在节点
        private boolean isEntered(Graph.Node node) {
            return heapIndexMap.containsKey(node);
        }

        // 是否在堆中
        private boolean isHeap(Graph.Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }
    }

}
