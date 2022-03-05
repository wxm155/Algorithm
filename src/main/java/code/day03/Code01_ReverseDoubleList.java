package code.day03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wxm
 * @created 2022/3/5
 */
public class Code01_ReverseDoubleList {

    /** 双向链表 */
    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }

    /**
     * 反转双向链表
     * @param head
     * @return
     */
    public static DoubleNode reverse(DoubleNode head){
        DoubleNode pre = null;
        DoubleNode next = null;
        while(head != null){
            // 记住下一个节点
            next = head.next;
            // 前后节点交换
            head.next = pre;
            head.last = next;
            // pre、head赋值
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     *  for test
     * @param head
     * @return
     */
    public static List<Integer> test(DoubleNode head){
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.value);
            head = head.next;
        }
        return list;
    }

    /**
     * for test
     * @param len
     * @param value
     * @return
     */
    public static DoubleNode generateRandomDoubleList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        DoubleNode pre = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
            pre.next = cur;
            cur.last = pre;
            pre = cur;
            size--;
        }
        return head;
    }

    public static void main(String[] args) {
        for (int j = 0; j < 500000; j++) {
            DoubleNode doubleNode = generateRandomDoubleList(20, 50);
            List<Integer> list = test(doubleNode);
            DoubleNode reverse = reverse(doubleNode);
            for (int i = list.size() - 1; i >=0 ; i--) {
                if (list.get(i) != reverse.value){
                    System.out.println("fuck....");
                }
                reverse = reverse.next;

            }
        }
        //
        //DoubleNode doubleNode = generateRandomDoubleList(20, 50);
        //DoubleNode print = doubleNode;
        //while (print != null){
        //    System.out.print(print.value + " ");
        //    print = print.next;
        //}
        //System.out.println();
        //List<Integer> list = test(doubleNode);
        //DoubleNode reverse = reverse(doubleNode);
        //for (int i = list.size() - 1; i >=0 ; i--) {
        //    System.out.print(list.get(i) + " ");
        //}
        //System.out.println();
        //System.out.println("------------------");
        //while (reverse != null){
        //    System.out.print(reverse.value + " ");
        //    reverse = reverse.next;
        //}
    }

}
