import java.util.*;

public class DijkstraAlgorithm {
    static class Edge {
        int to, weight;
        Edge(int to, int weight) { this.to = to; this.weight = weight; }
    }

    static int[] dijkstra(List<List<Edge>> graph, int source) {
        int n = graph.size();
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int dist = current[1];
            if (dist != distance[node]) continue;

            for (Edge edge : graph.get(node)) {
                int newDist = dist + edge.weight;
                if (newDist < distance[edge.to]) {
                    distance[edge.to] = newDist;
                    pq.offer(new int[]{edge.to, newDist});
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        int n = 6;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        addEdge(graph, 0, 1, 4);
        addEdge(graph, 0, 2, 2);
        addEdge(graph, 1, 2, 1);
        addEdge(graph, 1, 3, 5);
        addEdge(graph, 2, 3, 8);
        addEdge(graph, 2, 4, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 3, 5, 6);
        addEdge(graph, 4, 5, 3);

        int[] distance = dijkstra(graph, 0);
        System.out.println("Shortest distances from node 0:");
        for (int i = 0; i < distance.length; i++) {
            System.out.println("0 -> " + i + " = " + distance[i]);
        }
    }

    static void addEdge(List<List<Edge>> graph, int from, int to, int weight) {
        graph.get(from).add(new Edge(to, weight));
        graph.get(to).add(new Edge(from, weight));
    }
}
