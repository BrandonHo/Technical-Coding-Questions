import java.util.Arrays;

public class PowerGrid2 {

    public void Test() {

        char[] vertexNames = new char[] { 'A', 'B', 'C', 'D', 'E' };
        Graph graph = new Graph(vertexNames, 5, 6);

        graph.AddEdge(0, 0, 1, 1);
        graph.AddEdge(1, 1, 2, 4);
        graph.AddEdge(2, 1, 3, 6);
        graph.AddEdge(3, 3, 4, 5);
        graph.AddEdge(4, 2, 4, 1);
        graph.AddEdge(5, 1, 4, 2);

        graph.PerformKruskal();
    }

    public class Graph {

        class Edge implements Comparable<Edge> {
            public int src, dest, weight;

            @Override
            public int compareTo(PowerGrid2.Graph.Edge o) {
                return this.weight - o.weight;
            }
        }

        class Subset {
            int parent, rank;
        }

        public int numVertices, numEdges;
        public Edge[] edges;
        public char[] vertexNames;

        public Graph(char[] vertexNames, int numVertices, int numEdges) {
            this.numVertices = numVertices;
            this.numEdges = numEdges;
            this.vertexNames = vertexNames;
            edges = new Edge[numEdges];
            for (int i = 0; i < edges.length; i++)
                edges[i] = new Edge();
        }

        public void AddEdge(int edgeIndex, int src, int dest, int weight) {

            edges[edgeIndex].src = src;
            edges[edgeIndex].dest = dest;
            edges[edgeIndex].weight = weight;
        }

        /*
         * Utility function find the set associated with a vertex. Sets are uniquely
         * represented by a parent root vertex. Each vertex are initially within their
         * own set, and are union'ed with other sets when adding edges towards an MST.
         * 
         * This uses the path compression technique, where the root parent is referenced
         * in all children vertices of a set. This enables root parent vertex queries to
         * be really quick when checking if a new vertex belongs in a set.
         */
        public int FindRootParent(Subset[] subsets, int vertexIndex) {
            if (subsets[vertexIndex].parent != vertexIndex)
                subsets[vertexIndex].parent = FindRootParent(subsets, subsets[vertexIndex].parent);
            return subsets[vertexIndex].parent;
        }

        /*
         * Union of two disjoint sets. Union is done by rank. Rank determines the number
         * of unions a subset has undergone. Higher rank = bigger subset.
         */
        public void Union(Subset[] subsets, int vertexIndexA, int vertexIndexB) {
            int rootVertexIndexA = FindRootParent(subsets, vertexIndexA);
            int rootVertexIndexB = FindRootParent(subsets, vertexIndexB);

            // Attach smaller sub set/tree under the bigger sub set/tree
            if (subsets[rootVertexIndexA].rank < subsets[rootVertexIndexB].rank)
                subsets[rootVertexIndexA].parent = rootVertexIndexB;
            else if (subsets[rootVertexIndexA].rank > subsets[rootVertexIndexB].rank)
                subsets[rootVertexIndexB].parent = rootVertexIndexA;

            // If same size/rank, then arbitrarily make A root, B child
            else {
                subsets[rootVertexIndexB].parent = rootVertexIndexA;
                subsets[rootVertexIndexA].rank++;
            }
        }

        public void PerformKruskal() {

            // Array to store the edges of the MST (numEdges = numVertices - 1)
            Edge[] edgesInMST = new Edge[numVertices];
            for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++)
                edgesInMST[vertexIndex] = new Edge();

            // Create subset for each of the vertices
            Subset[] subsets = new Subset[numVertices];
            for (int vertexIndex = 0; vertexIndex < numVertices; vertexIndex++) {
                subsets[vertexIndex] = new Subset();

                // Set parent as itself, initial rank at 0
                subsets[vertexIndex].parent = vertexIndex;
                subsets[vertexIndex].rank = 0;
            }

            // Sort edges array (ascending, therefore min weight edge at start)
            Arrays.sort(edges);
            int edgeIndex = 0;
            int indexForEdgesInMST = 0;

            // Process until we have numEdges = numVertices - 1 (MST requirement)
            while (indexForEdgesInMST < numVertices - 1) {
                Edge nextEdge = edges[edgeIndex++];

                int rootForVertexSRC = FindRootParent(subsets, nextEdge.src);
                int rootForVertexDest = FindRootParent(subsets, nextEdge.dest);

                // If the root/parents are in different sets, then no cycle
                if (rootForVertexSRC != rootForVertexDest) {

                    // Add edge to MST edges array + union the sets
                    edgesInMST[indexForEdgesInMST++] = nextEdge;
                    Union(subsets, rootForVertexSRC, rootForVertexDest);
                }
            }

            // Display the MST edges
            for (int x = 0; x < numVertices; x++)
                System.out.println(vertexNames[edgesInMST[x].src] + " - " + vertexNames[edgesInMST[x].dest] + ": "
                        + edgesInMST[x].weight);
        }
    }

}
