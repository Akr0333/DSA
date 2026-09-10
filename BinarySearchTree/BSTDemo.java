public class BSTDemo {
    static class Node {
        int value;
        Node left, right;
        Node(int value) { this.value = value; }
    }

    static Node insert(Node root, int value) {
        if (root == null) return new Node(value);
        if (value < root.value) root.left = insert(root.left, value);
        else if (value > root.value) root.right = insert(root.right, value);
        return root;
    }

    static boolean search(Node root, int value) {
        if (root == null) return false;
        if (root.value == value) return true;
        return value < root.value ? search(root.left, value) : search(root.right, value);
    }

    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.value + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        Node root = null;
        for (int value : values) root = insert(root, value);

        System.out.print("Inorder traversal: ");
        inorder(root);
        System.out.println();
        System.out.println("Search 60: " + (search(root, 60) ? "Found" : "Not found"));
        System.out.println("Search 90: " + (search(root, 90) ? "Found" : "Not found"));
    }
}
