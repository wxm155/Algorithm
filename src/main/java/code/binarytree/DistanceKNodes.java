package code.binarytree;

import java.util.*;

/**
 * @author: wxm
 * @created: 2022/11/28
 */
public class DistanceKNodes {

    /**
     * 给定三个参数，二叉树的头节点head，树上某个节点target，正数K。
     * 从target开始，可以向上走或者向下走，返回与target的距离是K的所有节点
     */

    private static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    /**
     * @param root   根节点
     * @param target 目标节点
     * @param K      距离
     * @return 返回节点集合
     */
    public static List<Node> distanceKNodes(Node root, Node target, int K) {
        Map<Node, Node> parent = new HashMap<>();
        parent.put(root, null);
        creatParent(root, parent);

        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        List<Node> ans = new ArrayList<>();
        queue.offer(target);
        visited.add(target);
        int curLevel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 批处理同一层的节点
            while (size-- > 0) {
                Node cur = queue.poll();
                if (curLevel == K) {
                    ans.add(cur);
                }
                if (cur.left != null && !visited.contains(cur.left)) {
                    queue.add(cur.left);
                    visited.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    queue.add(cur.right);
                    visited.add(cur.right);
                }
                Node parentNode = parent.get(cur);
                if (parentNode != null && !visited.contains(parentNode)) {
                    queue.add(parentNode);
                    visited.add(parentNode);
                }
            }
            curLevel++;
            if (curLevel > K) {
                break;
            }
        }
        return ans;
    }

    /**
     * 生成父节点
     *
     * @param node      当前节点
     * @param parentMap 父map
     */
    public static void creatParent(Node node, Map<Node, Node> parentMap) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            parentMap.put(node.left, node);
            creatParent(node.left, parentMap);
        }
        if (node.right != null) {
            parentMap.put(node.right, node);
            creatParent(node.right, parentMap);
        }
    }

    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = distanceKNodes(root, target, K);
        for (Node o1 : ans) {
            System.out.println(o1.value);
        }

    }
}
