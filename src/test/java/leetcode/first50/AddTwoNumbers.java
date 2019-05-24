package leetcode.first50;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = null;
        ListNode node = null;
        int next = 0;
        while (l1 != null || l2 != null || next == 1) {
            int l1val, l2val;
            l1val = (l1 != null) ? l1.val : 0;
            l2val = (l2 != null) ? l2.val : 0;

            int sum = l1val + l2val;
            if (sum + next >= 10) {
                sum = sum - 10 + next;
                next = 1;
            } else {
                sum = sum + next;
                next = 0;
            }
            ListNode tmp = new ListNode(sum);
            if (node == null) {
                node = tmp;
                root = tmp;
            } else {
                node.next = tmp;
                node = node.next;
            }

            l1 = (l1 == null) ? null : l1.next;
            l2 = (l2 == null) ? null : l2.next;
        }
        return root;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
