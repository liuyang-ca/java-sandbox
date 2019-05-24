package leetcode.second50;

import org.junit.Test;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 *
 * Example 1:
 *
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * Example 2:
 *
 * Input: 1->1->1->2->3
 * Output: 2->3
 */
public class RemoveDuplicatesFromSortedList2 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = new ListNode(-1);
        cur.next = head;
        ListNode result = cur;
        boolean isDelete = false;
        while(cur != null && cur.next != null && cur.next.next != null) {
            if(cur.next.val == cur.next.next.val) {
                cur.next = cur.next.next;
                isDelete = true;
            } else {
                if (isDelete) {
                    cur.next = cur.next.next;
                    isDelete = false;
                } else {
                    cur = cur.next;
                }
            }
        }

        if (isDelete && cur != null && cur.next != null) {
            cur.next = cur.next.next;
        }

        return result.next;
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
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        ListNode r = deleteDuplicates(l1);
        while(r != null) {
            System.out.println(r.val);
            r = r.next;
        }
    }
}
