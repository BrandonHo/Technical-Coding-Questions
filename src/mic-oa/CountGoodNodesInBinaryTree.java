/*
    https://leetcode.com/problems/count-good-nodes-in-binary-tree/
*/
public class CountGoodNodesInBinaryTree {
    
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val)
        {
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
        TreeNode testRoot = new TreeNode(3, new TreeNode(1, new TreeNode(3), null), new TreeNode(4, new TreeNode(1), new TreeNode(5)));
        System.out.println(Solve(testRoot));
    }

    /*
        Time Complexity: O(2^h), where h refers to the height of the binary tree.

        Space Complexity: O(h), where h refers to the height of the binary tree.
        This occurs due to recursion stack.
    */
    public int Solve(TreeNode root)
    {
        if (root == null)
            return 0;

        // Account for root node always being a good node + check children
        return 1 + DFSHelper(root.left, root.val) + DFSHelper(root.right, root.val);
    }

    public int DFSHelper(TreeNode node, int maxValue)
    {
        if (node == null)
            return 0;

        // Add 1 if node is greater than current max value in the path + check children
        if (node.val >= maxValue)
            return 1 + DFSHelper(node.left, node.val) + DFSHelper(node.right, node.val);
        return DFSHelper(node.left, maxValue) + DFSHelper(node.right, maxValue);
    }
}
