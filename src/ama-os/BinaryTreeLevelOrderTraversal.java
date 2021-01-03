import java.util.ArrayList;
import java.util.List;

/*
    https://leetcode.com/problems/binary-tree-level-order-traversal/
*/
public class BinaryTreeLevelOrderTraversal {
    
    public class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {

        }

        public TreeNode(int val)
        {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right)
        {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void Test()
    {
        TreeNode testRoot = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        DisplayResults(levelOrder(testRoot));
    }

    public void DisplayResults(List<List<Integer>> results)
    {
        for (List<Integer> result : results)
        {
            for (Integer resultNumber : result)
            {
                System.out.print(resultNumber + " ");
            }
            System.out.println();
        }
    }

    /*
        Time Complexity: O(n), where n refers to the total number of nodes in the tree.

        Space Complexity: O(n) where n refers to the total number of nodes in the tree.

        Note: The space complexities are probably closer towards O(2^h), where h refers
        to the height of the tree, but easier to probably estimate it at worst to be O(n).
    */
    public List<List<Integer>> levelOrder(TreeNode root)
    {
        // Edge case for undefined root
        if (root == null)
            return new ArrayList<List<Integer>>();
        
        // List (used as a queue) that will store nodes for the next level to process
        List<TreeNode> nextLevelNodeQueue = new ArrayList<TreeNode>();
        nextLevelNodeQueue.add(root);

        // List (used as a queue) that will store nodes for the current level
        List<TreeNode> currLevelNodeQueue = new ArrayList<TreeNode>();
        
        // List that will store all node values for the current level
        ArrayList<Integer> nodeValuesInCurrLevel;

        // Result array which will store a list of node values per levels
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        
        // Iterate until there are no longer any nodes for the next level
        while (nextLevelNodeQueue.size() != 0)
        {
            // Add all parent nodes to current level node queue + clear the next node queue
            currLevelNodeQueue.addAll(nextLevelNodeQueue);
            nextLevelNodeQueue.clear();

            // Initialise list to hold the new node values for the level
            nodeValuesInCurrLevel = new ArrayList<Integer>();

            // Iterate through all nodes in the current level
            while (currLevelNodeQueue.size() != 0)
            {
                // Add value of the node to list that holds node values for the level
                TreeNode aNode = currLevelNodeQueue.remove(0);
                nodeValuesInCurrLevel.add(aNode.val);

                // If children defined, add to the next node queue
                if (aNode.left != null)
                    nextLevelNodeQueue.add(aNode.left);
                if (aNode.right != null)
                    nextLevelNodeQueue.add(aNode.right);
            }
            
            // Lastly to conclude the iteration, add the node values for th current level to results list
            results.add(nodeValuesInCurrLevel);
        }

        return results;
    }
}
