/*
    https://leetcode.com/problems/validate-binary-search-tree/
*/
public class ValidateBinarySearchTree {
    
    public void Test()
    {
        TreeNode testRoot = new TreeNode(5, new TreeNode(4), new TreeNode(6, new TreeNode(3), new TreeNode(7)));
        TreeNode testRoot2 = new TreeNode(-2147483648, null, new TreeNode(2147483647));

        System.out.println(Solve(testRoot));
        System.out.println(Solve(testRoot2));
    }

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

    /*
        Time Complexity: O(n) where n refers to the number of items in the BST. We simply iterate
        through the entire tree and check whether there is a violation in the minimum/maximum thresholds

        Space Complexity: O(1).
    */
    public boolean Solve(TreeNode root)
    {
        if (root == null)
            return true;
        
        return BSTHelper(root.left, Long.MIN_VALUE, root.val) && 
             BSTHelper(root.right, root.val, Long.MAX_VALUE);
    }

    /*
        This method is used for checking both the left side and right side of the
        tree, therefore both minimum and maximum thresholds are maintained.

        Longs are used for the thresholds as the problem constraints indicate
        that node values can be Integer.MAX_VALUE or Integer.MIN_VALUE.

        i.e:
        - We maintain the max value threshold for the left side of a tree, as any value
        on the left side of the tree should not exceed the maximum threshold for it
        to be a valid BST.
        - Likewise, we maintain the minimum value threshold for the right side of the tree,
        as any value on the right side of the tree should not be below the minimum threshold for
        it to be a valid BST.
    */
    public boolean BSTHelper(TreeNode node, long minValue, long maxValue)
    {
        if (node == null)
            return true;
        
        if (node.val <= minValue || node.val >= maxValue)
            return false;
        
        return BSTHelper(node.left, minValue, node.val) && BSTHelper(node.right, node.val, maxValue);
    }
}
