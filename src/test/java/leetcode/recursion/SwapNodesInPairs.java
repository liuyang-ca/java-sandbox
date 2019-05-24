package leetcode.recursion;

import org.junit.Test;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class SwapNodesInPairs {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode swapPairs(ListNode head) {
        if(head != null && head.next != null) {
            ListNode nextNext = head.next.next;
            ListNode tmp = head;
            head = head.next;
            head.next = tmp;
            head.next.next = swapPairs(nextNext);
        }

        return head;
    }

    @Test
    public void Sandbox() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode head = swapPairs(l1);

        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
