package cz.braza.leetcode;

/*
19. Remove Nth Node From End of List
Medium
Topics
Companies
Hint
Given the head of a linked list, remove the nth node from the end of the list and return its head.



Example 1:


Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]
Example 2:

Input: head = [1], n = 1
Output: []
Example 3:

Input: head = [1,2], n = 1
Output: [1]


Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 */
public class L19RemoveNthNodeFromEndOfList {
    /*
    We create two list elements, that should be N items apart, and move them together
    Once the deeper one points at the end, the other points at parent to be removed.

    Runs 0ms, beats 100%, 208 testcases
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) return null;
        ListNode start = head;
        ListNode inside = head;
        // go down n items:
        for (int i = 0; i < n; i++) inside = inside.next;
        // if we are at the end, return next from start (we are to remove 1st element = head)
        if (inside == null) return head.next;
        // now look for last element:
        while (inside.next != null) {
            inside = inside.next;
            start = start.next;
        }
        // now inside should point to the last element, and start points at PARENT of element to be removed:
        start.next = start.next.next; // this will remove that element from the list
        return head;
    }
}
