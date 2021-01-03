import java.util.ArrayList;

/*
    Given an N-ary tree, find the subtree with the maximum average. Return the root of the subtree.
    A subtree of a tree is the node which have at least 1 child plus all its descendants.
    The average value of a subtree is the sum of its values, divided by the number of nodes.

    Input:
         20
        /   \
       12     18
    /  |  \   / \
    11  2  3 15  8

    Output: 18
    Explanation:
    There are 3 nodes which have children in this tree:
    12 => (11 + 2 + 3 + 12) / 4 = 7
    18 => (18 + 15 + 8) / 3 = 13.67
    20 => (12 + 11 + 2 + 3 + 18 + 15 + 8 + 20) / 8 = 11.125

    18 has the maximum average so output 18.
*/
public class SubtreeWithMaxAverage {

    public class TreeNode {

        public int NodeVal;
        public ArrayList<TreeNode> ChildNodes;

        public TreeNode(int nodeVal) {
            NodeVal = nodeVal;
            ChildNodes = new ArrayList<TreeNode>();
        }
    }

    public void Test() {

        TreeNode root = new TreeNode(20);

        TreeNode child1 = new TreeNode(12);
        child1.ChildNodes.add(new TreeNode(11));
        child1.ChildNodes.add(new TreeNode(2));
        child1.ChildNodes.add(new TreeNode(3));
        root.ChildNodes.add(child1);

        TreeNode child2 = new TreeNode(18);
        child2.ChildNodes.add(new TreeNode(15));
        child2.ChildNodes.add(new TreeNode(8));
        root.ChildNodes.add(child2);

        PerformSubtreeWithMaxAverage(root);
        System.out.println(maxAverage);
        System.out.println(maxNode.NodeVal);
    }

    private double maxAverage;
    private TreeNode maxNode;

    /*
     * Space Complexity: O(1), only constants stored (max average, max node, various
     * counts and sums)
     * 
     * Time Complexity: O(n), need to traverse through each node to calculate the
     * sum, no repeated work.
     */
    public double[] PerformSubtreeWithMaxAverage(TreeNode root) {

        if (root == null)
            return new double[] { 0, 0 };

        double count = 1;
        double sum = root.NodeVal;

        // Ignore nodes without any children
        if (root.ChildNodes.size() != 0) {

            // Add up sum and count for each child (subtree)
            for (TreeNode node : root.ChildNodes) {
                double[] cur = PerformSubtreeWithMaxAverage(node);
                sum += cur[0];
                count += cur[1];
            }

            // Check if average > current max average
            double average = sum / count;
            if (count > 1 && average > maxAverage) {
                maxAverage = average;
                maxNode = root;
            }
        }

        return new double[] { sum, count };
    }
}