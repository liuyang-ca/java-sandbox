package leetcode.recursion;

import org.junit.Test;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        helper(result, l1, l2);
        return result.next;
    }

    private void helper(ListNode result, ListNode l1, ListNode l2) {
        if(l1 == null) {
            result.next = l2;
            return;
        } else if(l2 == null) {
            result.next = l1;
            return;
        }

        if(l1.val < l2.val) {
            result.next = l1;
            helper(result.next, l1.next, l2);
        } else {
            result.next = l2;
            helper(result.next, l1, l2.next);
        }
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    @Test
    public void Sandbox() {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(4);
        a1.next = a2;
        a2.next = a3;

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(3);
        ListNode b3 = new ListNode(4);
        b1.next = b2;
        b2.next = b3;

        ListNode head = mergeTwoLists(a1, b1);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

}
