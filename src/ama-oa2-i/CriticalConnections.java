import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
    https://leetcode.com/problems/critical-connections-in-a-network/
*/
public class CriticalConnections {

    public class GraphNode {
        int val;
        int pathStep;
        Set<GraphNode> edges;

        public GraphNode(int val) {
            this.val = val;
            this.pathStep = 0;
            this.edges = new HashSet<>();
        }
    }

    public void Test() {

        ArrayList<List<Integer>> testConnections = new ArrayList<List<Integer>>();
        testConnections.add(Arrays.asList(0, 1));
        testConnections.add(Arrays.asList(1, 2));
        testConnections.add(Arrays.asList(2, 0));
        testConnections.add(Arrays.asList(1, 3));
        testConnections.add(Arrays.asList(3, 4));

        ArrayList<List<Integer>> testConnections2 = new ArrayList<List<Integer>>();
        testConnections2.add(Arrays.asList(1, 2));
        testConnections2.add(Arrays.asList(1, 3));
        testConnections2.add(Arrays.asList(2, 3));
        testConnections2.add(Arrays.asList(3, 4));
        testConnections2.add(Arrays.asList(4, 5));
        testConnections2.add(Arrays.asList(4, 6));
        testConnections2.add(Arrays.asList(5, 6));
        testConnections2.add(Arrays.asList(5, 7));
        testConnections2.add(Arrays.asList(6, 7));
        testConnections2.add(Arrays.asList(7, 8));
        testConnections2.add(Arrays.asList(8, 9));
        testConnections2.add(Arrays.asList(8, 10));
        testConnections2.add(Arrays.asList(10, 9));

        // DisplayResult(FindCriticalConnections(4, testConnections));
        DisplayResult(FindCriticalConnections(10, testConnections2));
    }

    public void DisplayResult(List<List<Integer>> result) {
        for (List<Integer> connection : result)
            System.out.println("[" + connection.get(0) + "," + connection.get(1) + "]");
    }

    /*
        Time Complexity: O(m) [adding m connections to hash map] + O(m) [DFS],
        or ~O(m), where m refers to the number of connections.

        Space Complexity: O(n.n), [n nodes, n-1 values in set], where n refers to the number
        of nodes.
    */
    public List<List<Integer>> FindCriticalConnections(int n, List<List<Integer>> connections) {

        List<List<Integer>> critConns = new ArrayList<List<Integer>>();
        if (n == 0)
            return critConns;

        // Construct graph hash map using specified connections
        HashMap<Integer, GraphNode> graph = new HashMap<Integer, GraphNode>();
        for (List<Integer> conn : connections) {

            // Add graph nodes...
            graph.putIfAbsent(conn.get(0), new GraphNode(conn.get(0)));
            graph.putIfAbsent(conn.get(1), new GraphNode(conn.get(1)));

            // ...Then add their connections
            graph.get(conn.get(0)).edges.add(graph.get(conn.get(1)));
            graph.get(conn.get(1)).edges.add(graph.get(conn.get(0)));
        }

        int step = 1;
        int startChildNodeVal = 1;
        GraphNode dummyNode = new GraphNode(-1);

        // Start from graph node with specified startChildNodeVal
        DFS(critConns, step, dummyNode, graph.get(startChildNodeVal));

        return critConns;
    }

    public int DFS(List<List<Integer>> critConns, int step, GraphNode parent, GraphNode child) {

        // Has the child been visited before? return its path step.
        if (child.pathStep != 0)
            return child.pathStep;

        // Assign child with path step + increment step appropriately
        child.pathStep = step++;

        // Try to find if there are alternate path steps (edges to earlier visted nodes)
        int minPathStep = Integer.MAX_VALUE;
        for (GraphNode childEdge : child.edges) {

            // Ignore edge to parent
            if (childEdge.val == parent.val)
                continue;

            // Find the smallest path step amongst edges
            minPathStep = Math.min(minPathStep, DFS(critConns, step, child, childEdge));
        }

        // If no smaller path step, then edge between parent and child is critical
        if (minPathStep >= child.pathStep && parent.val != -1)
            critConns.add(Arrays.asList(parent.val, child.val));

        // Return the smallest path step
        return Math.min(minPathStep, child.pathStep);
    }
}
