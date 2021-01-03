import java.util.PriorityQueue;

/*
    https://leetcode.com/problems/merge-k-sorted-lists/
*/
public class MergeKSortedLists {
    

	public void Test()
    {
        ListNode[] lists = new ListNode[]{
            new ListNode(1, new ListNode(4, new ListNode(5, null))),
            new ListNode(1, new ListNode(3, new ListNode(4, null))),
            new ListNode(2, new ListNode(6, null))
        };

        // DisplayResult(SolvePQ(lists));
        DisplayResult(SolveMergeTwoPairs(lists));
    }

    public void DisplayResult(ListNode result)
    {
        while (result != null)
        {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public class ListNode
    {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next)
        {
            this.val = val;
            this.next = next;
        }
    }

    /*
        Time Complexity: O(n log n) + O(n.[log n + log n]), where n refers to the
        number of lists of list nodes.

        - O(n log n) refers to adding n list nodes to the PQ

        - O(m.[log n + log n]) refers to retrieving n list nodes from the PQ and adding
        n list nodes to the PQ (with PQ always having height log n).

        Space Complexity: O(h), where h refers to the number of lists.
    */
    public ListNode SolvePQ(ListNode[] lists)
    {
        // Create PQ and add each of the head nodes to PQ
        PriorityQueue<ListNode> listPQ = new PriorityQueue<>((a, b) -> (a.val - b.val));
        for (ListNode node : lists)
            listPQ.offer(node);

        ListNode resultHead = new ListNode(-1, null);
        ListNode tempHead = resultHead;

        // Repeat adding nodes to result until PQ empty
        while (listPQ.size() != 0)
        {
            // Add next smallest number to result head
            ListNode nextNode = listPQ.poll();
            tempHead.next = nextNode;
            tempHead = tempHead.next;

            // If nextNode has another number in list -> add that to PQ
            if (nextNode.next != null)
                listPQ.offer(nextNode.next);
        }

        // Return the true result head (next node)
        return resultHead.next;
    }

    /*
        Time Complexity: O(n.c), where n refers to number of lists of list nodes and
        c refers to the maximum size for a list in the lists of list nodes.

        Space Complexity: O(1).
    */
    public ListNode SolveMergeTwoPairs(ListNode[] lists)
    {
        // Edge case
        if (lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];

        // Get first list...
        ListNode resultList = lists[0];

        // ... Then merge subsequent lists with the first list
        for (int listIndex = 1; listIndex < lists.length; listIndex++)
            resultList = MergeTwoLists(resultList, lists[listIndex]);

        return resultList;
    }

    public ListNode MergeTwoLists(ListNode a, ListNode b)
    {
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (a.val <= b.val)
        {
            a.next = MergeTwoLists(a.next, b);
            return a;
        }
        else
        {
            b.next = MergeTwoLists(a, b.next);
            return b;
        }
    }
}
