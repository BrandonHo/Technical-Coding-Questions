import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;

/*
    Your team at amazon is overseeing the design of a new high-efficiency data center at HQ2.
    A power grid need to be generated for supplying power to N servers.
    All servers in the grid have to be connected such that they have access to power.
    The cost of connections between different servers varies.

    Assume that there are no ties, names of servers are unique, 
    connections are directionless, there is at most one connection between a pair of servers,
    all costs are greater than zero, and a server does not connect to itself.

    Write an algorithm to minimize the cost of connecting all servers in the power grid.

    Inputs
    num, an Integer representing number of connections.
    connectons, representing a list of connections where each element of the list consists of two servers and the cost of connection between the servers.

    Note
    The cost of connection between the servers is always greater than 0.

    Example:

    Input
    num = 5

    connection =
        [[A,B,1],
        [B,C,4],
        [B,D,6],
        [D,E,5],
        [C,E,1]]
        
    Output
        [[A,B,1],
        [B,C,4],
        [C,E,1],
        [D,E,5]]

    Note: This is basically a Minimum Spanning Tree problem.
*/
public class PowerGrid {

    public void Test() {

        char[] vertexNames = new char[] { 'A', 'B', 'C', 'D', 'E' };
        Graph graph = new Graph(vertexNames);

        graph.AddEdge(0, 1, 1);
        graph.AddEdge(1, 2, 4);
        graph.AddEdge(1, 3, 6);
        graph.AddEdge(3, 4, 5);
        graph.AddEdge(2, 4, 1);

        graph.PrimAlgorithm();
    }

    public class Node {

        int destination;
        int weight;

        Node(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public class Graph {

        char[] vertexNames;
        int numVertices;
        LinkedList<Node>[] vertexToAdjacentNodes;

        public Graph(char[] vertexNames) {

            this.vertexNames = vertexNames;
            this.numVertices = vertexNames.length;
            vertexToAdjacentNodes = new LinkedList[numVertices];

            for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++)
                vertexToAdjacentNodes[vertexIndex] = new LinkedList<>();
        }

        public void AddEdge(int source, int destination, int weight) {
            Node srcNode = new Node(source, weight);
            Node destNode = new Node(destination, weight);

            vertexToAdjacentNodes[source].addLast(destNode);
            vertexToAdjacentNodes[destination].addLast(srcNode);
        }

        public class NodePQ {
            int vertex;
            int currMinWeight;
        }

        public class NodePQComparator implements Comparator<NodePQ> {
            @Override
            public int compare(NodePQ o1, NodePQ o2) {
                return o1.currMinWeight - o2.currMinWeight;
            }
        }

        /*
         * Note - Code is from geeksforgeeks. However, there are several parts that are
         * not following prim's algorithm (in my opinion).
         * 
         * Time Complexity: O(V + E). O(Log V)
         */
        public void PrimAlgorithm() {

            // Array to store the parent indices of nodes in the graph
            int[] parent = new int[numVertices];

            // This array is used to check what nodes that we have in the MST
            Boolean[] mstSet = new Boolean[numVertices];

            /*
             * This array is used to store edge information.
             * 
             * The index is used to refer to the index of the source node, while the object
             * is used to refer to the destination node.
             */
            NodePQ[] edges = new NodePQ[numVertices];

            // Initialisation...
            for (int i = 0; i < numVertices; i++) {

                // Each of the available nodes
                edges[i] = new NodePQ();
                edges[i].currMinWeight = Integer.MAX_VALUE;
                edges[i].vertex = i;

                // MST and parent arrays
                mstSet[i] = false;
                parent[i] = -1;
            }

            // Select the first node for processing (arbitrary selection)
            mstSet[0] = true;
            edges[0].currMinWeight = 0;

            // Initialise tree set as a priority queue (better remove performance O(n))
            TreeSet<NodePQ> queue = new TreeSet<NodePQ>(new NodePQComparator());

            queue.add(edges[0]);

            while (!queue.isEmpty()) {

                // Get next available node with smallest weight
                NodePQ selectedNode = queue.pollFirst();
                mstSet[selectedNode.vertex] = true;

                // Iterate through connected edges of the selected node
                for (Node connectedNode : vertexToAdjacentNodes[selectedNode.vertex]) {

                    // Only consider node if not already in MST set/tree (will form cycle)
                    if (!mstSet[connectedNode.destination]) {

                        // Update with new weight if it is less than connected node weight
                        if (edges[connectedNode.destination].currMinWeight > connectedNode.weight) {

                            // Remove, update, then add back to queue to update its position
                            queue.remove(edges[connectedNode.destination]);
                            edges[connectedNode.destination].currMinWeight = connectedNode.weight;
                            queue.add(edges[connectedNode.destination]);

                            // Lastly, update the parent of the connected node as the selected node
                            parent[connectedNode.destination] = selectedNode.vertex;
                        }
                    }
                }
            }

            // Display the result (ignore 0th index since there should be V - 1 edges)
            for (int i = 1; i < numVertices; i++)
                System.out.println(vertexNames[parent[i]] + " - " + vertexNames[i] + ": " + edges[i].currMinWeight);
        }
    }
}
