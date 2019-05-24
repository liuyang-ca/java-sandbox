package leetcode.first50;

import leetcode.MyLogger;
import org.junit.Test;

import java.util.*;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class MergeKSortedLists {

    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode root = new ListNode(Integer.MIN_VALUE);
        ListNode head = root;

        while(true) {
            int smallestIndex = -1;
            for (int i=0; i<lists.length; i++) {
                if(lists[i] != null) {
                    MyLogger.LOGGER.debug("index = {}, val = {}, next.val = {}", i, lists[i].val, lists[i].next == null ? null : lists[i].next.val);
                    while(lists[i] != null && lists[i].val == root.val) {
                        root.next = lists[i];
                        root = root.next;
                        lists[i] = lists[i].next;
                    }
                    if(lists[i] != null && (smallestIndex == -1 || lists[smallestIndex].val > lists[i].val)) {
                        smallestIndex = i;
                    }
                }
            }
            if (smallestIndex == -1) {
                break;
            } else {
                root.next = lists[smallestIndex];
                root = root.next;
                lists[smallestIndex] = lists[smallestIndex].next;
            }
        }

        return head.next;
    }

    private ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        ListNode head = new ListNode(1);
        ListNode root = head;
        while (head1 != null || head2 != null) {
            if (head1 == null) {
                root.next = head2;
                break;
            } else if (head2 == null) {
                root.next = head1;
                break;
            } else {
                if (head1.val > head2.val) {
                    root.next = head2;
                    head2 = head2.next;
                } else {
                    root.next = head1;
                    head1 = head1.next;
                }
            }
            root = root.next;
        }

        return head.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        } else if (lists.length == 1) {
            return lists[0];
        }

        int low=0, high=lists.length - 1;
        List<ListNode> arrayList = new ArrayList<>();
        while(low <= high) {
            if(low == high) {
                arrayList.add(lists[low++]);
            } else {
                arrayList.add(mergeTwoLists(lists[low++], lists[high--]));
            }
        }
        ListNode[] results = new ListNode[arrayList.size()];
        results = arrayList.toArray(results);
        return mergeKLists(results);
    }

    @Test
    public void Sandbox() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode head = mergeKLists(new ListNode[]{l1, l2, l3});
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    @Test
    public void Sandbox2() {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i< 10; i++) {
            list.add(i);
        }
        list.add(2, -2);
        System.out.println(Arrays.toString(list.toArray()));
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
