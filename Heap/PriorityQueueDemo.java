import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int[] values = {40, 10, 30, 20, 50, 15};
        for (int value : values) minHeap.offer(value);

        System.out.println("Elements in ascending order:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();
        System.out.println("Time complexity: O(n log n) for inserting n elements and removing them.");
        System.out.println("Space complexity: O(n)");
    }
}
