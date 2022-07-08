package code.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()){
            stack.push(queue.poll());
        }
        return postDes(stack);
    }

    // 左右中 -> 中右左
    public static Node postDes(Stack<Integer> stack){
        Integer pop = stack.pop();
        if (pop == null){
            return null;
        }
        Node head = new Node(pop);
        head.right = postDes(stack);
        head.left = postDes(stack);
        return head;
    }

    /**
     * 按层遍历序列化
     * @param head
     * @return
     */
    public static Queue<Integer> levelSerialize(Node head) {
        Queue<Integer> ans = new LinkedList<>();
        if (head == null) {
            ans.offer(null);
        } else {
            Queue<Node> levelQueue = new LinkedList<>();
            levelQueue.offer(head);
            while (!levelQueue.isEmpty()) {
                Node poll = levelQueue.poll();
                ans.offer(poll.value);
                if (poll.left != null) {
                    levelQueue.offer(poll.left);
                    ans.offer(poll.left.value);
                } else {
                    ans.offer(null);
                }
                if (poll.right != null) {
                    levelQueue.offer(poll.right);
                    ans.offer(poll.right.value);
                } else {
                    ans.offer(null);
                }
            }
        }
        return ans;
    }

    /**
     * 按层遍历反序列化
     * @param queue
     * @return
     */
    public static Node levelDeserialize(Queue<Integer> queue){
        if (queue == null || queue.size() == 0){
            return null;
        }
        Node head = generateNode(queue.poll());
        if (head == null){
            return null;
        }
        Queue<Node> levelQueue = new LinkedList<>();
        levelQueue.offer(head);
        while (!levelQueue.isEmpty()){
            Node node = levelQueue.poll();
            node.left = generateNode(queue.poll());
            node.right = generateNode(queue.poll());
            if (node.left != null){
                levelQueue.offer(node.left);
            }
            if (node.right != null){
                levelQueue.offer(node.right);
            }
        }
        return head;
    }

    /**
     * 生成节点
     * @param value
     * @return
     */
    public static Node generateNode(Integer value){
        if (value == null){
            return null;
        }
        return new Node(value);
    }


}
