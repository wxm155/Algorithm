package code.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author: wxm
 * @created: 2022/07/13
 */
public class UnionFind {

    /**
     * 并查集：并查集是一种树型的数据结构，用于处理一些不相交集合的合并及查询问题（即所谓的并、查）
     */

    public static class Node{
        int value;
        public Node(int value){
            this.value = value;
        }
    }

    public static class MyUnionFind{
        Map<Node,Node> nodes;
        Map<Node,Node> parents;
        Map<Node,Integer> sizeMap;

        public MyUnionFind(List<Node> nodeList){
            int initSize = (int)(nodeList.size() / 0.75 + 1);
            nodes = new HashMap<>(initSize);
            parents = new HashMap<>(initSize);
            sizeMap = new HashMap<>(initSize);
            for (Node node : nodeList) {
                nodes.put(node,node);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }

        // 查找自己的父亲节点
        public Node findFather(Node cur){
            Stack<Node> stack = new Stack<>();
            while (cur != parents.get(cur)){
                stack.push(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()){
                parents.put(stack.pop(),cur);
            }
            return cur;
        }

        // 是否是同一组
        public boolean isSameSet(Node a,Node b){
            return parents.get(a) == parents.get(b);
        }

        // 将小集合合并到大集合中
        public void union(Node a,Node b){
            Node aFather = findFather(a);
            Node bFather = findFather(b);
            if (aFather != bFather){
                Integer aSize = sizeMap.get(a);
                Integer bSize = sizeMap.get(b);
                Node max = aSize > bSize ? aFather : bFather;
                Node min = max == bFather ? aFather : bFather;
                parents.put(min,max);
                sizeMap.put(max,aSize + bSize);
                sizeMap.remove(min);
            }
        }

        // 获取存在的集合个数
        public int sets(){
            return sizeMap.size();
        }

    }
}
