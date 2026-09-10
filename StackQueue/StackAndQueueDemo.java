import java.util.*;

public class StackAndQueueDemo {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(10); stack.push(20); stack.push(30);
        System.out.println("Stack: " + stack);
        System.out.println("Popped: " + stack.pop());

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(100); queue.offer(200); queue.offer(300);
        System.out.println("Queue: " + queue);
        System.out.println("Removed: " + queue.poll());
    }
}
