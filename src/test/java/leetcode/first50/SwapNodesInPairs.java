package leetcode.first50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 Given a linked list, swap every two adjacent nodes and return its head.

 You may not modify the values in the list's nodes, only nodes itself may be changed.



 Example:

 Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode root = new ListNode(1);
        ListNode result = root;
        result.next = head;
        while(root.next != null && root.next.next !=null) {
            ListNode tmp1 = root.next;
            ListNode tmp2 = root.next.next;
            ListNode tmp3 = root.next.next.next;

            root.next = tmp2;
            root.next.next = tmp1;
            root.next.next.next = tmp3;


            root = root.next.next;
        }

        return result.next;
    }

    @Test
    public void Sandbox() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
//        l1.next.next = new ListNode(3);
//        l1.next.next.next = new ListNode(4);


        ListNode head = swapPairs(l1);
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
