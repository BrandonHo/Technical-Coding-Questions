/*
    https://leetcode.com/problems/subtree-of-another-tree/
*/
public class SubtreeOfAnotherTree {

    public class TreeNode {
        int val;
        TreeNode left, right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void Test() {
        TreeNode maintreeTest1 = new TreeNode(3,
                new TreeNode(4, new TreeNode(1, null, null), new TreeNode(2, null, null)), new TreeNode(5, null, null));
        TreeNode subtreeTest1 = new TreeNode(4, new TreeNode(1, null, null), new TreeNode(2, null, null));

        TreeNode maintreeTest2 = new TreeNode(3,
                new TreeNode(4, new TreeNode(1, null, null), new TreeNode(2, new TreeNode(0, null, null), null)),
                new TreeNode(5, null, null));
        TreeNode subtreeTest2 = new TreeNode(4, new TreeNode(1, null, null), new TreeNode(2, null, null));

        System.out.println(IsSubtree(maintreeTest1, subtreeTest1));
        System.out.println(IsSubtree(maintreeTest2, subtreeTest2));
    }

    /*
     * Time Complexity: O(s).O(t), where s refers to size of the tree s and t refers
     * to the size of the tree t.
     * 
     * Space Complexity: O(1)
     */
    public boolean IsSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null)
            return true;
        if (s != null && t == null)
            return true;
        if (s == null && t != null)
            return false;

        if (s.val == t.val)
            if (HelperCheck(s.left, t.left) && HelperCheck(s.right, t.right))
                return true;

        return IsSubtree(s.left, t) || IsSubtree(s.right, t);
    }

    public boolean HelperCheck(TreeNode s, TreeNode t) {

        if (s == null && t == null)
            return true;
        if (s != null && t == null)
            return false;
        if (s == null && t != null)
            return false;

        if (s.val == t.val)
            return HelperCheck(s.left, t.left) && HelperCheck(s.right, t.right);

        return false;
    }
}
