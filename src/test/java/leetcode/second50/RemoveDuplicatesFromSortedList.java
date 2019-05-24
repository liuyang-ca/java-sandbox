package leetcode.second50;

import org.junit.Test;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 * <p>
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {

        ListNode root = head;
        while(root != null) {
            while(root.next != null && root.next.val == root.val) {
                root.next = root.next.next;
            }

            root = root.next;
        }

        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode root = head.next;
        ListNode current = new ListNode(head.val);
        ListNode result = current;
        while(root != null) {
            if(root.val != current.val) {
                current.next = new ListNode(root.val);
                current = current.next;
            }

            root = root.next;
        }

        return result;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    @Test
    public void Sandbox() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode r = deleteDuplicates2(l1);
        while(r != null) {
            System.out.println(r.val);
            r = r.next;
        }
    }
}
