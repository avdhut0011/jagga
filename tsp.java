import java.util.Arrays;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    int[] path;
    double bound;
    int level;

    public Node(int[] path, double bound, int level) {
        this.path = path;
        this.bound = bound;
        this.level = level;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.bound, other.bound);
    }
}

public class TSPBranchAndBound {

    public static int[] tspBranchAndBound(double[][] graph) {
        int n = graph.length;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // Create a root node
        Node root = new Node(new int[]{0}, bound(graph, new int[]{0}, n), 0);
        pq.add(root);

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();

            if (currentNode.level == n - 1) {
                int[] path = Arrays.copyOf(currentNode.path, n + 1);
                path[n] = path[0]; // Complete the cycle
                return path;
            }

            for (int i = 0; i < n; i++) {
                if (!contains(currentNode.path, i)) {
                    int[] childPath = Arrays.copyOf(currentNode.path, currentNode.path.length + 1);
                    childPath[childPath.length - 1] = i;
                    double childBound = bound(graph, childPath, n);

                    if (childBound != Double.POSITIVE_INFINITY) {
                        Node childNode = new Node(childPath, childBound, currentNode.level + 1);
                        pq.add(childNode);
                    }
                }
            }
        }

        return null;
    }

    public static double bound(double[][] graph, int[] path, int n) {
        double totalCost = 0;

        // Calculate the cost of the edges in the path
        for (int i = 0; i < path.length - 1; i++) {
            totalCost += graph[path[i]][path[i + 1]];
        }

        // Add the cost of the last edge to the starting node
        totalCost += graph[path[path.length - 1]][path[0]];

        // Calculate the minimum outgoing edge cost for each node not in the path
        for (int i = 0; i < n; i++) {
            if (!contains(path, i)) {
                double minEdgeCost = Double.POSITIVE_INFINITY;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        minEdgeCost = Math.min(minEdgeCost, graph[i][j]);
                    }
                }
                totalCost += minEdgeCost;
            }
        }

        return totalCost;
    }

    public static boolean contains(int[] array, int element) {
        for (int value : array) {
            if (value == element) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Example graph represented as an adjacency matrix
        double[][] exampleGraph = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        int[] path = tspBranchAndBound(exampleGraph);

        System.out.println("Optimal TSP Path: " + Arrays.toString(path));
    }
}
