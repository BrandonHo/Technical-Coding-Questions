/*
    https://leetcode.com/problems/same-tree/
*/
public class SameTree {
    
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
        TreeNode testRootA = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode testRootB = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode testRootC = new TreeNode(1, new TreeNode(3), new TreeNode(3));

        System.out.println(isSameTree(testRootA, testRootB));
        System.out.println(isSameTree(testRootA, testRootC));
    }

    /*
        Time Complexity: O(n) where n refers to the number of items in the trees
        p and q.

        Space Complexity: O(1).
    */ 
    public boolean isSameTree(TreeNode p, TreeNode q)
    {
        if (p == null && q == null)
            return true;
        if ((p == null && q != null) || (p != null && q == null))
            return false;

        if (p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;
    }
}
