public class RotateList {
    
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

    public void Test()
    {
        ListNode test = new ListNode(1, null);
        AddLast(test, 2);
        AddLast(test, 3);
        AddLast(test, 4);
        AddLast(test, 5);

        ListNode test2 = new ListNode(0, null);
        AddLast(test2, 1);
        AddLast(test2, 2);

        // DisplayResults(SolveUsingAdditionalLinkedList(test, 2));
        // DisplayResults(SolveUsingAdditionalLinkedList(test2, 4));

        DisplayResults(SolveByMovingPointers(test, 2));
        DisplayResults(SolveByMovingPointers(test2, 4));
    }

    public void DisplayResults(ListNode head)
    {
        ListNode temp = head;
        while (temp != null)
        {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /*
        Time Complexity: O(n) + O(n), or ~O(n), where n refers to the size of the list.
        The first O(n) occurs from finding the length of the list, while the second O(n)
        occurs from moving pointers around.

        Space Complexity: O(1)
    */
    public ListNode SolveByMovingPointers(ListNode head, int k)
    {
        // Edge cases
        if (head == null)
            return null;
        if (k == 0)
            return head;

        // Calculate list length + actual number of rotations
        int listLength = FindLength(head);
        int numberOfRotations = k >= listLength? k % listLength : k;

        // Another edge case - may not need to rotate at all
        if (listLength == 1 || numberOfRotations == 0)
            return head;

        // Iterate to the node before the node with number that needs to be rotated to beginning...
        ListNode oldHeadIterator = head;
        int counter = listLength - numberOfRotations - 1;
        while (counter != 0)
        {
            counter -= 1;
            oldHeadIterator = oldHeadIterator.next;
        }

        // ... Then make the following node be the beginning of new head + nullify tail of old head
        ListNode newHead = oldHeadIterator.next;
        oldHeadIterator.next = null;

        // Iterate to end of the new head, then simply add the old head contents to the tail
        ListNode newHeadIterator = newHead;
        while (newHeadIterator.next != null)
            newHeadIterator = newHeadIterator.next;
        newHeadIterator.next = head;

        return newHead;
    }

    /*
        Time Complexity: O(n) + O(n.n), where n refers to the size of the list.

        - O(n) refers to finding the length of the list

        - O(n.n) refers to iterating n times to target rotation numbers and then the
        original starting numbers. These n numbers are then inserted into a linked list
        copy, which occurs in O(n) time.

        Space Complexity: O(n), where n refers to the size of the list. A copy of the
        linked list is made to accomodate the rotated elements.
    */
    public ListNode SolveUsingAdditionalLinkedList(ListNode head, int k)
    {
        // Edge cases
        if (head == null)
            return null;
        if (k == 0)
            return head;

        // Create blank list to store new result
        ListNode result = new ListNode(-1, null);

        // Find the length of the list
        int listLength = FindLength(head);

        // Calculate the actual number of rotations (since k can be greater than list length)
        int numberOfRotations = k >= listLength? k % listLength : k;

        // Another edge case - may not need to rotate at all
        if (listLength == 1 || numberOfRotations == 0)
            return head;

        // Iterate through list until we get to the numbers that need to be moved to beginning...
        int counter = listLength - numberOfRotations;
        ListNode temp = head;
        while (counter != 0)
        {
            temp = temp.next;
            counter -= 1;
        }

        // ... Then add the rotated numbers to list
        while (temp != null)
        {
            AddLast(result, temp.val);
            temp = temp.next;
        }

        // Lastly, reset counters and add the remaining numbers to the back
        counter = listLength - numberOfRotations;
        temp = head;
        while (counter != 0)
        {
            AddLast(result, temp.val);
            temp = temp.next;
            counter -= 1;
        }

        // Since result is dummy node, return result.next
        return result.next;
    }

    public int FindLength(ListNode head)
    {
        if (head == null)
            return 0;

        ListNode temp = head;
        int length = 0;
        while (temp != null)
        {
            length += 1;
            temp = temp.next;
        }

        return length;
    }

    public void AddLast(ListNode head, int val)
    {
        if (head == null)
        {
            head = new ListNode(val, null);
            return;
        }

        ListNode temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = new ListNode(val, null);
    }
}
