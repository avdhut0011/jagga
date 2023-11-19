import java.util.*;
class Edge {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class BF {
    static void bellmanFord(int[] distance, Edge[] edges, int vertices, int edgesCount, int source) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        // Relax all edges |V| - 1 times
        for (int i = 1; i < vertices; i++) {
            for (int j = 0; j < edgesCount; j++) {
                int u = edges[j].source;
                int v = edges[j].destination;
                int weight = edges[j].weight;

                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                }
            }
        }

        // Check for negative cycles
        for (int j = 0; j < edgesCount; j++) {
            int u = edges[j].source;
            int v = edges[j].destination;
            int weight = edges[j].weight;

            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                System.out.println("Graph contains a negative cycle!");
                return;
            }
        }

        // Print the distances
        System.out.println("Shortest distances from the source vertex:");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vertex " + i + ": " + distance[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edgesCount = scanner.nextInt();

        Edge[] edges = new Edge[edgesCount];

        System.out.println("Enter the details of each edge (source destination weight):");
        for (int i = 0; i < edgesCount; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            edges[i] = new Edge(source, destination, weight);
        }

        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();

        int[] distance = new int[vertices];

        //long startTime = System.nanoTime();
        bellmanFord(distance, edges, vertices, edgesCount, source);
        //long endTime = System.nanoTime();

        //long duration = (endTime - startTime) / 1000000; // in milliseconds
        //System.out.println("Time taken: " + duration + " ms");

        scanner.close();
    }
}

