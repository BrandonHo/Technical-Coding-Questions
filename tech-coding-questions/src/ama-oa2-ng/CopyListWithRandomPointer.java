import java.util.HashMap;

/*
    https://leetcode.com/problems/copy-list-with-random-pointer/
*/
public class CopyListWithRandomPointer {

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public void Test() {

        Node head = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        node1.random = head;
        node2.random = node4;
        node3.random = node2;
        node4.random = head;

        // Debug in editor to see copy
        // Node newDeepCopy = CopyRandomList(head);
    }

    /*
     * Space complexity: O(n), node hash map that houses the node copies.
     * 
     * Time complexity: O(n) [one pass to add new node copies to map] + O(n) [second
     * pass to update node pointers]. Therefore, ~O(n).
     */
    public Node CopyRandomList(Node head) {

        // Edge case
        if (head == null)
            return null;

        // Create a map that uses original nodes as keys, and node copies as values
        HashMap<Node, Node> nodeMap = new HashMap<Node, Node>();
        Node tempNode = head;

        // Add original nodes as keys, new node copies as values (only values for now)
        while (tempNode != null) {
            nodeMap.put(tempNode, new Node(tempNode.val));
            tempNode = tempNode.next;
        }

        tempNode = head;

        // Now update next and random pointers of the node copies using map
        while (tempNode != null) {
            nodeMap.get(tempNode).next = nodeMap.get(tempNode.next);
            nodeMap.get(tempNode).random = nodeMap.get(tempNode.random);
            tempNode = tempNode.next;
        }

        // Return head via node map
        return nodeMap.get(head);
    }
}
