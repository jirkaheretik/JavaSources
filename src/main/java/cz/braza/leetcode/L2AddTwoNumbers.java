package cz.braza.leetcode;

public class L2AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode last = result;
        int carryOver = 0;
        while (l1 != null || l2 != null) {
            int value = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carryOver;
            ListNode ln = new ListNode(value % 10);
            carryOver = value / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            last.next = ln;
            last = ln;
        }
        if (carryOver > 0) {
            last.next = new ListNode(carryOver);
        }
        return result.next; // first item is empty, so return the next one
    }
}
