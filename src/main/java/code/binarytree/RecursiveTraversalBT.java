package code.binarytree;

/**
 * @author wxm
 * @created 2022/7/4
 */
public class RecursiveTraversalBT {

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
     * @param head
     */
    public static void prePrint(Node head){
        if (head == null){
            return;
        }
        System.out.println(head.value);
        prePrint(head.left);
        prePrint(head.right);
    }

    /**
     * 二叉树的中序遍历
     * @param head
     */
    public static void inPrint(Node head){
        if (head == null){
            return;
        }
        inPrint(head.left);
        System.out.println(head.value);
        inPrint(head.right);
    }

    /**
     * 二叉树的后序遍历
     * @param head
     */
    public static void postPrint(Node head){
        if (head == null){
            return;
        }
        postPrint(head.left);
        postPrint(head.right);
        System.out.println(head.value);
    }

}
