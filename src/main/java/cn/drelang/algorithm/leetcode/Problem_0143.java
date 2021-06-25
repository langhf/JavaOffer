package cn.drelang.algorithm.leetcode;

/**
 *
 * @author Drelang
 * @date 2021/4/29 20:28
 */

public class Problem_0143 {
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode l1 = head, l2 = slow.next;
        slow.next = null;

        l2 = reverseList(l2);

        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null) {
            cur.next = l1;
            cur = cur.next;
            l1 = l1.next;
            // l1必须要先移动！！！，才能进行后面的 cur = cur.next，否则会改变 l1 后续节点
            cur.next = l2;
            cur = cur.next;
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        head = dummyHead.next;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Problem_0143 problem = new Problem_0143();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        problem.reorderList(head);
    }
}

