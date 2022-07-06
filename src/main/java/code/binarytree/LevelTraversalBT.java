package code.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: wxm
 * @created: 2022/07/05
 */
public class LevelTraversalBT {

    /**
     * 按层遍历二叉树
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
     * 通过队列实现二叉树的按层遍历
     * @param head
     */
    public static void levelPrint(Node head){
        if (head != null){
            Queue<Node> queue = new LinkedList<>();
            queue.offer(head);
            while (!queue.isEmpty()){
                Node node = queue.poll();
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
                System.out.print(node.value + " ");
            }
        }
        System.out.println();
    }
}
