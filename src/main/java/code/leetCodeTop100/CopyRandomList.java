package code.leetCodeTop100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wxm
 * @created 2024/2/3
 */
public class CopyRandomList {

    /**
     * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
     * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
     * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态复制链表中的指针都不应指向原链表中的节点 。
     * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，
     * 同样有 x.random --> y 。返回复制链表的头节点。
     * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
     * val：一个表示 Node.val 的整数。
     * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
     * 你的代码 只 接受原链表的头节点 head 作为传入参数。
     * <p>
     * 示例 1：
     * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
     * <p>
     * 示例 2：
     * 输入：head = [[1,1],[2,1]]
     * 输出：[[1,1],[2,1]]
     * <p>
     * 示例 3：
     * 输入：head = [[3,null],[3,0],[3,null]]
     * 输出：[[3,null],[3,0],[3,null]]
     * <p>
     * 提示：
     * 0 <= n <= 1000
     * -10^4 <= Node.val <= 10^4
     * Node.random 为 null 或指向链表中的节点。
     * <p>
     * 力扣：https://leetcode.cn/problems/copy-list-with-random-pointer/description/?envType=study-plan-v2&envId=top-100-liked
     */


    public Node copyRandomList(Node head) {
        Map<Node,Node> cacheMap = new HashMap<>();
        return process(head,cacheMap);
    }

    public Node process(Node head, Map<Node, Node> cacheMap) {
        if (head == null) {
            return null;
        }
        if (!cacheMap.containsKey(head)) {
            Node newNode = new Node(head.val);
            cacheMap.put(head, newNode);
            newNode.next = process(head.next, cacheMap);
            newNode.random = process(head.random, cacheMap);
        }
        return cacheMap.get(head);
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
