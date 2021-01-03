/*
    https://leetcode.com/problems/merge-two-sorted-lists/
*/
public class MergeTwoSortedLists {

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public void Test() {
        ListNode test1L1 = new ListNode(1, new ListNode(2, new ListNode(4, null)));
        ListNode test1L2 = new ListNode(1, new ListNode(3, new ListNode(4, null)));
        ListNode result = RecursiveMerge(test1L1, test1L2);
        DisplayListNode(result);
    }

    public void DisplayListNode(ListNode result) {
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    /*
     * Time Complexity: O(n + m), where n and m refer to the sizes of lists l1 and
     * l2 respectively.
     * 
     * Space Complexity: O(1), only manipulating pointers.
     */
    public ListNode RecursiveMerge(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        if (l1.val < l2.val) {
            l1.next = RecursiveMerge(l1.next, l2);
            return l1;
        } else {
            l2.next = RecursiveMerge(l1, l2.next);
            return l2;
        }
    }

    /*
     * Time Complexity: O(n + m), where n and m refer to the sizes of lists l1 and
     * l2 respectively.
     * 
     * Space Complexity: O(1), only created one dummy node + only manipulating
     * pointers.
     */
    public ListNode IterativeMerge(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode newList = new ListNode(0, null);
        ListNode pointer = newList;

        while (true) {
            if (l1 == null) {
                pointer.next = l2;
                break;
            }

            if (l2 == null) {
                pointer.next = l1;
                break;
            }

            if (l1.val < l2.val) {
                pointer.next = l1;
                l1 = l1.next;
                pointer = pointer.next;
            } else {
                pointer.next = l2;
                l2 = l2.next;
                pointer = pointer.next;
            }
        }

        return newList.next;
    }
}
