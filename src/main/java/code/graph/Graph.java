package code.graph;

import java.util.List;

/**
 * @author wxm
 * @created 2022/7/17
 */
public class Graph {

    // 点的描述
    public static class Node{
        public int value;
        public int out;
        public int in;
        public List<Node> nexts;
        public List<Edge> edges;

        public Node(int value,int out,int in,List<Node> nexts,List<Edge> edges){
            this.value = value;
            this.out = out;
            this.in = in;
            this.nexts = nexts;
            this.edges = edges;
        }
    }

    // 边的描述
    public static class Edge{
        public int weight;
        public Node from;
        public Node to;
        public Edge(int weight,Node from,Node to){
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }
}
