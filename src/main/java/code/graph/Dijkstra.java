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

    public static HashMap<Graph.Node, Integer> dijkstra1(Graph.Node from){
        // 对应点和到对应点的最小距离
        HashMap<Graph.Node,Integer> distanceMap = new HashMap<>();
        Set<Graph.Node> selectedSet = new HashSet<>();
        distanceMap.put(from,0);
        Graph.Node minNode = getMinDistanceUnSelected(distanceMap, selectedSet);
        while (minNode != null){
            Integer distance = distanceMap.get(minNode);
            for (Graph.Edge edge : minNode.edges) {
                Graph.Node toNode = edge.to;
                if (distanceMap.containsKey(toNode)){
                    // 当前点的距离到下一个点的距离取最小值
                    distanceMap.put(toNode,Math.min(distanceMap.get(toNode),distance + edge.weight));
                }else {
                    distanceMap.put(toNode,distance + edge.weight);
                }
                selectedSet.add(minNode);
                minNode = getMinDistanceUnSelected(distanceMap,selectedSet);
            }
        }
        return distanceMap;
    }

    /**
     * 获取最小距离并且没有并选中过的点
     * @param distanceMap
     * @param selectedSet
     * @return
     */
    public static Graph.Node getMinDistanceUnSelected(HashMap<Graph.Node,Integer> distanceMap,Set<Graph.Node> selectedSet){
        Graph.Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Graph.Node, Integer> entry : distanceMap.entrySet()) {
            Graph.Node node = entry.getKey();
            Integer distance = entry.getValue();
            if (!selectedSet.contains(node) && distance < minDistance){
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }
}
