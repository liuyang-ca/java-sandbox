package leetcode.first50;

import org.junit.Test;

import java.util.Map;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 *
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        ListNode root = new ListNode(1111);
        ListNode result = root;
        result.next = head;

        ListNode[] array = new ListNode[k+1];
        boolean isNull = false;
        ListNode node = root;
        for(int i=0; i<k; i++) {
            if (node.next == null) {
                isNull = true;
                break;
            }
            array[i] = node.next;
            node = node.next;
        }

        while(!isNull) {
            array[k] = node.next;
            for(int i=k-1; i>=0; i--) {
                root.next = array[i];
                root = root.next;
            }
            root.next = array[k];

            node = root;
            for(int i=0; i<k; i++) {
                if (node.next == null) {
                    isNull = true;
                    break;
                }
                array[i] = node.next;
                node = node.next;

            }
        }

        return result.next;
    }

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
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        l1.next.next.next.next.next = new ListNode(6);
        l1.next.next.next.next.next.next = new ListNode(7);


        ListNode head = reverseKGroup(l1, 3);
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
