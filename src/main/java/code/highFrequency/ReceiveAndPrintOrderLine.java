package code.highFrequency;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxm
 * @created 2022/11/26
 */
public class ReceiveAndPrintOrderLine {

    /**
     * 已知一个消息流会不断地吐出整数1~N，但不一定按照顺序依次吐出，
     * 如果上次打印的序号为i， 那么当i+1出现时 请打印i+1及其之后接收过的并且连续的所有数，
     * 直到1~N全部接收并打印完，请设计这种接收并打印的结构
     */

    private static class Node {
        public String value;
        public Node next;

        public Node(String value) {
            this.value = value;
        }
    }

    /**
     * 数组实现造成内存泄漏
     */
    private static class MessageBox {
        private Map<Integer, Node> head;
        private Map<Integer, Node> tail;
        private int waitPoint;

        public MessageBox() {
            head = new HashMap<>();
            tail = new HashMap<>();
            waitPoint = 1;
        }

        public void receive(int num, String info) {
            if (num < 1) {
                return;
            }
            Node cur = new Node(info);
            // num...num
            head.put(num, cur);
            tail.put(num, cur);
            if (tail.containsKey(num - 1)) {
                tail.get(num - 1).next = cur;
                tail.remove(num - 1);
                head.remove(num);
            }
            if (head.containsKey(num + 1)) {
                cur.next = head.get(num + 1);
                head.remove(num + 1);
                tail.remove(num);
            }
            if (num == waitPoint) {
                print();
            }

        }

        private void print() {
            Node node = head.remove(waitPoint);
            while (node != null) {
                System.out.print(node.value + " ");
                node = node.next;
                waitPoint++;
            }
            tail.remove(waitPoint - 1);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // MessageBox only receive 1~N
        MessageBox box = new MessageBox();
        box.receive(2, "B");
        box.receive(1, "A");
        box.receive(4, "D");
        box.receive(5, "E");
        box.receive(7, "G");
        box.receive(8, "H");
        box.receive(6, "F");
        box.receive(3, "C");
        box.receive(9, "I");
        box.receive(10, "J");
        box.receive(12, "L");
        box.receive(13, "M");
        box.receive(11, "K");
    }
}
