import java.util.ArrayList;

/*
    https://leetcode.com/problems/symmetric-tree/
*/
public class SymmetricTree {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right)
        {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void Test()
    {
        TreeNode testRoot = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), 
            new TreeNode(2, new TreeNode(4), new TreeNode(3)));

        TreeNode testRoot2 = new TreeNode(1, new TreeNode(2), new TreeNode(2));

        System.out.println(SolveDFS(testRoot));
        System.out.println(SolveDFS(testRoot2));
    }

    public boolean SolveDFS(TreeNode root)
    {
        if (root == null)
            return true;

        return AreTreesMirror(root.left, root.right);
    }

    /*
        Time Complexity: O(n), where n refers to the number of nodes in the tree.

        Space Complexity: O(h), where h refers to the height of the tree. This specifically
        refers to the size of the recursion call stack.
    */
    public boolean AreTreesMirror(TreeNode nodeA, TreeNode nodeB)
    {
        // Edge case - if both false, then symmetrical, return true
        if (nodeA == null && nodeB == null)
            return true;
        
        // Edge case - if there is a mismatch in node null, then return false
        if (nodeA == null || nodeB == null)
            return false;
        
        // If node values equal -> Compare inner and outer subtrees for symmetry
        if (nodeA.val == nodeB.val)
            return AreTreesMirror(nodeA.left, nodeB.right) && AreTreesMirror(nodeA.right, nodeB.left);
        
        // If node values not equal, then not symmetrical -> return false
        return false;
    }

    /*
        Time Complexity: O(n).O(2^[h + 1]), where n refers to the number of nodes in the tree
        and h refers to the height of the tree.

        - O(n) refers to iterating through the various nodes in the tree.

        - O(2^[h + 1]) refers to the adding of nodes to the queue during iterations of the queue.

        Adding child nodes to a list/queue usually take O(n) time.
        The algorithm, however, removes nodes during each iteration.
        Therefore, the actual worst case time of adding nodes to list/queue is closer towards O(2^[h + 1]), which
        refers to the number of nodes on the bottom row.

        Space Complexity: O(2^(h + 1)), where h refers to the height of the tree. This refers to the worst
        case for space, where you handle a complete/leveled binary tree.
        
        *** Note that I factored +1 with the height for the space and time complexity,
        since the function will add/check "null" objects at the bottom row.
    */
    public boolean SolveBFS(TreeNode root)
    {
        // Base edge case - if root null, then return true (null is symmetric)
        if (root == null)
            return true;

        // Base case - add root children to list/queue
        ArrayList<TreeNode> queueNodes = new ArrayList<TreeNode>();
        queueNodes.add(root.left);
        queueNodes.add(root.right);

        // Iterate until no more children
        while (queueNodes.size() != 0)
        {
            TreeNode nodeA = queueNodes.remove(0);
            TreeNode nodeB = queueNodes.remove(0);

            // Edge case - if there is mismatch in null value, then not symmetrical, return false
            if ((nodeA != null && nodeB == null) || (nodeA == null && nodeB != null))
                return false; 
            
            // If botn not null, proceed to check values
            if (nodeA != null && nodeB != null)
            {
                if (nodeA.val != nodeB.val)
                    return false;

                // Add outer children to check for symmetry
                queueNodes.add(nodeA.left);
                queueNodes.add(nodeB.right);

                // Add inner children to check for symmetry
                queueNodes.add(nodeA.right);
                queueNodes.add(nodeB.left);
            }
            // If both null, simply move on to next iteration
        }

        return true;
    }
}
