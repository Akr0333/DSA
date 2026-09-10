public class SinglyLinkedList {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    Node head;

    void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) { head = newNode; return; }
        Node current = head;
        while (current.next != null) current = current.next;
        current.next = newNode;
    }

    void delete(int data) {
        if (head == null) return;
        if (head.data == data) { head = head.next; return; }
        Node current = head;
        while (current.next != null && current.next.data != data) current = current.next;
        if (current.next != null) current.next = current.next.next;
    }

    void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertAtEnd(10); list.insertAtEnd(20); list.insertAtEnd(30); list.insertAtEnd(40);
        System.out.print("List: "); list.display();
        list.delete(20);
        System.out.print("After deleting 20: "); list.display();
    }
}
