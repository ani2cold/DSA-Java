/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int first = -1;
        int prev = -1;

        int min = Integer.MAX_VALUE;
        int max = -1;

        int index = 1;

        ListNode previous = head;
        ListNode current = head.next;

        while (current.next != null) {

            if ((current.val > previous.val && current.val > current.next.val) ||
                (current.val < previous.val && current.val < current.next.val)) {

                // Current node is a critical point

                if (first == -1) {
                    first = index;
                } else {
                    min = Math.min(min, index - prev);
                    max = index - first;
                }

                prev = index;
            }

            previous = current;
            current = current.next;
            index++;
        }

        if (first == -1 || first == prev) {
            return new int[]{-1, -1};
        }

        return new int[]{min, max};
    }
}