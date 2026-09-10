import java.util.*;

public class GraphTraversal {
    static void bfs(Map<Integer, List<Integer>> graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);

        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int next : graph.getOrDefault(node, Collections.emptyList())) {
                if (visited.add(next)) queue.offer(next);
            }
        }
        System.out.println();
    }

    static void dfs(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited) {
        visited.add(node);
        System.out.print(node + " ");
        for (int next : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(next)) dfs(graph, next, visited);
        }
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Arrays.asList(1, 4, 5));
        graph.put(3, Arrays.asList(1, 6));
        graph.put(4, Collections.singletonList(2));
        graph.put(5, Collections.singletonList(2));
        graph.put(6, Collections.singletonList(3));

        bfs(graph, 1);
        System.out.print("DFS: ");
        dfs(graph, 1, new HashSet<>());
        System.out.println();
    }
}
