package code.leetCodeTop100;

/**
 * @author wxm
 * @created 2024/2/3
 */
public class DetectCycle {

    /**
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
     * 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，
     * 则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 不允许修改 链表。
     * <p>
     * 示例 1：
     * 输入：head = [3,2,0,-4], pos = 1
     * 输出：返回索引为 1 的链表节点
     * 解释：链表中有一个环，其尾部连接到第二个节点。
     * <p>
     * 示例 2：
     * 输入：head = [1,2], pos = 0
     * 输出：返回索引为 0 的链表节点
     * 解释：链表中有一个环，其尾部连接到第一个节点。
     * <p>
     * 示例 3：
     * 输入：head = [1], pos = -1
     * 输出：返回 null
     * 解释：链表中没有环。
     * <p>
     * 提示：
     * 链表中节点的数目范围在范围 [0, 10^4] 内
     * -10^5 <= Node.val <= 10^5
     * pos 的值为 -1 或者链表中的一个有效索引
     * <p>
     * 进阶：你是否可以使用 O(1) 空间解决此题？
     * <p>
     * 力扣：https://leetcode.cn/problems/linked-list-cycle-ii/description/?envType=study-plan-v2&envId=top-100-liked
     */

    //双指针的第一次相遇：
    //设两指针 fast，slow 指向链表头部 head 。
    //令 fast 每轮走 222 步，slow 每轮走 111 步。
    //执行以上两步后，可能出现两种结果：
    //
    //第一种结果： fast 指针走过链表末端，说明链表无环，此时直接返回 null。
    //
    //如果链表存在环，则双指针一定会相遇。因为每走 111 轮，fast 与 slow 的间距 +1+1+1，fast 一定会追上 slow 。
    //
    //第二种结果： 当fast == slow时， 两指针在环中第一次相遇。下面分析此时 fast 与 slow 走过的步数关系：
    //
    //设链表共有 a+ba+ba+b 个节点，其中 链表头部到链表入口 有 aaa 个节点（不计链表入口节点）， 链表环 有 bbb 个节点（这里需要注意，aaa 和 bbb 是未知数，例如图解上链表 a=4a=4a=4 , b=5b=5b=5）；设两指针分别走了 fff，sss 步，则有：
    //
    //fast 走的步数是 slow 步数的 222 倍，即 f=2sf = 2sf=2s；（解析： fast 每轮走 222 步）
    //fast 比 slow 多走了 nnn 个环的长度，即 f=s+nbf = s + nbf=s+nb；（ 解析： 双指针都走过 aaa 步，然后在环内绕圈直到重合，重合时 fast 比 slow 多走 环的长度整数倍 ）。
    //将以上两式相减得到 f=2nbf = 2nbf=2nb，s=nbs = nbs=nb，即 fast 和 slow 指针分别走了 2n2n2n，nnn 个环的周长。
    //
    //接下来该怎么做呢？
    //
    //如果让指针从链表头部一直向前走并统计步数k，那么所有 走到链表入口节点时的步数 是：k=a+nbk=a+nbk=a+nb ，即先走 aaa 步到入口节点，之后每绕 111 圈环（ bbb 步）都会再次到入口节点。而目前 slow 指针走了 nbnbnb 步。因此，我们只要想办法让 slow 再走 aaa 步停下来，就可以到环的入口。
    //
    //但是我们不知道 aaa 的值，该怎么办？依然是使用双指针法。考虑构建一个指针，此指针需要有以下性质：此指针和 slow 一起向前走 a 步后，两者在入口节点重合。那么从哪里走到入口节点需要 aaa 步？答案是链表头节点head。
    //
    //链接：https://leetcode.cn/problems/linked-list-cycle-ii/solutions/12616/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
