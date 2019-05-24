package leetcode.first50;

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
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if(l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode head = root;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                root.next = l2;
                break;
            } else if (l2 == null) {
                root.next = l1;
                break;
            } else {
                if (l1.val == l2.val) {
                    root.next = new ListNode(l1.val);
                    root.next.next = new ListNode(l1.val);
                    l1 = l1.next;
                    l2 = l2.next;
                    root = root.next.next;
                } else if (l1.val < l2.val) {
                    root.next = new ListNode(l1.val);
                    l1 = l1.next;
                    root = root.next;
                } else {
                    root.next = new ListNode(l2.val);
                    l2 = l2.next;
                    root = root.next;
                }
            }
        }

        return head.next;
    }

    @Test
    public void Sandbox() {
        ListNode head = mergeTwoLists(new ListNode(0), null);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
