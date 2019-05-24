package leetcode.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Reverse a singly linked list.
 *
 * Example:
 * 4->NULL
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }

        ListNode output = null;
        output = reverseListHelper(head, output);
        return output;
    }

    private ListNode reverseListHelper(ListNode input, ListNode output) {
        if(input == null) {
            return output;
        }

        ListNode nextTemp = input.next;
            input.next = output;
            output = input;
            input = nextTemp;

            return reverseListHelper(input, output);
    }


    /*
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
     */
    public ListNode reverseList3(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;

        while(cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp;
        }

        return pre;
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

        ListNode head = reverseList(l1);
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }

        System.out.println("-------");
        head = l1;
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
