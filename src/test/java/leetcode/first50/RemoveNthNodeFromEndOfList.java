package leetcode.first50;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode root = new ListNode(123);
        root.next = head;
        ListNode low = root;
        ListNode high = root;

        while(high.next != null) {
            if(n <= 0 ) {
                low = low.next;
            }
            high = high.next;
            n--;
        }
       // System.out.println(low.val);
        if(low.next != null) {
            low.next = low.next.next;
        }

        return root.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> map = new HashMap<Integer, ListNode>();
        int i = 0;
        while(head != null) {
            map.put(i, head);
            head = head.next;
            i++;
        }

        int indexToRemove = i - n;
        if (indexToRemove == 0) {
            return map.get(1);
        } else if (indexToRemove == i - 1) {
            map.get(indexToRemove - 1).next = null;
        } else {
            map.get(indexToRemove - 1).next = map.get(indexToRemove + 1);
        }

        return map.get(0);
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        List<ListNode> list = new ArrayList<ListNode>();
        int i = 0;
        while(head != null) {
            list.add(head);
            head = head.next;
            i++;
        }

        int indexToRemove = i - n;
        if (indexToRemove == 0) {
            if (i == 1) {
                return null;
            }
            return list.get(1);
        } else if (indexToRemove == i - 1) {
            list.get(indexToRemove - 1).next = null;
        } else {
            list.get(indexToRemove - 1).next = list.get(indexToRemove + 1);
        }

        return list.get(0);
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
        ListNode root = new ListNode(1);
        ListNode head = root;
        for(int i=2; i<=5; i++) {
            root.next = new ListNode(i);
            root = root.next;
        }

        head = removeNthFromEnd3(head, 2);
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
