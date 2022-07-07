package code.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: wxm
 * @created: 2022/07/06
 */
public class SerializeAndReconstructTree {

    /**
     * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     * 比如如下两棵树
     *         __2
     *        /
     *       1
     *       和
     *       1__
     *          \
     *           2
     * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
     *
     */

    private static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 前序遍历序列化
     * @param head
     * @return
     */
    public static Queue<Integer> preSerialize(Node head){
        Queue<Integer> queue = new LinkedList<>();
        preSe(head,queue);
        return queue;
    }

    public static void preSe(Node head, Queue<Integer> queue){
        if (head == null){
            queue.offer(null);
        }else {
            queue.offer(head.value);
            preSe(head.left,queue);
            preSe(head.right,queue);
        }
    }

    /**
     * 前序遍历反序列化
     * @param queue
     * @return
     */
    public static Node preDeserialize(Queue<Integer> queue){
        if (queue == null || queue.size() == 0){
            return null;
        }
        return preDes(queue);
    }

    public static Node preDes(Queue<Integer> queue){
        Integer poll = queue.poll();
        if (poll == null){
            return null;
        }
        Node head = new Node(poll);
        head.left = preDes(queue);
        head.right = preDes(queue);
        return head;
    }

    /**
     * 后序遍历序列化
     * @param head
     * @return
     */
    public static Queue<Integer> postSerialize(Node head){
        Queue<Integer> queue = new LinkedList<>();
        postSe(head,queue);
        return queue;
    }

    public static void postSe(Node head,Queue<Integer> queue){
        if(head == null){
            queue.offer(null);
        }else {
            postSe(head.left,queue);
            postSe(head.right,queue);
            queue.offer(head.value);
        }
    }

    /**
     * 后序遍历反序列化
     * @param queue
     * @return
     */
    public static Node postDeserialize(Queue<Integer> queue){
        if (queue == null || queue.size() == 0){
            return null;
        }
        return postDes(queue);
    }

    public static Node postDes(Queue<Integer> queue){
        Integer poll = queue.poll();
        if (poll == null){
            return null;
        }
        Node head = new Node(poll);
        head.left = postDes(queue);
        head.right = postDes(queue);
        return head;
    }

}
