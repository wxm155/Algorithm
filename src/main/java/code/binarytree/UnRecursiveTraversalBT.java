package code.binarytree;

import java.util.Stack;

/**
 * @author: wxm
 * @created: 2022/07/05
 */
public class UnRecursiveTraversalBT {

    /**
     * 非递归实现二叉树的前、中、后序遍历
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
     * 二叉树的前序遍历
     *
     * @param head
     */
    public static void prePrint(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                Node pop = stack.pop();
                System.out.print(pop.value + " ");
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            }
        }
        System.out.println();
    }

    /**
     * 二叉树的中序遍历
     *
     * @param head
     */
    public static void inPrint(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    Node pop = stack.pop();
                    System.out.print(pop.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }


    /**
     * 二叉树的后序遍历
     *
     * @param head
     */
    public static void postPrint(Node head) {
        if (head != null){
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()){
                Node pop = s1.pop();
                s2.push(pop);
                if (head.left != null){
                    s1.push(head.left);
                }
                if (head.right != null){
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()){
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    /**
     * 二叉树的后序遍历
     *
     * @param head
     */
    public static void postPrint2(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && head != c.left && head != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && head != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    head = c;
                }
            }
        }
        System.out.println();
    }
}
