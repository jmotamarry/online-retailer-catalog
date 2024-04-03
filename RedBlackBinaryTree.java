import java.util.Date;

public class RedBlackBinaryTree {
    private Node root;

    public RedBlackBinaryTree() {
        this.root = null;
    }

    public void insert(Product product) {
        root = insertRecursive(root, product);
        root.color = Node.BLACK;
    }

    private Node insertRecursive(Node root, Product product) {
        if (root == null) {
            return new Node(product);
        }

        if (product.hashCode() < root.product.hashCode()) {
            root.left = insertRecursive(root.left, product);
        } else if (product.hashCode() > root.product.hashCode()) {
            root.right = insertRecursive(root.right, product);
        } else {
        }

        if (isRed(root.right) && !isRed(root.left))
            root = rotateLeft(root);
        if (isRed(root.left) && isRed(root.left.left))
            root = rotateRight(root);
        if (isRed(root.left) && isRed(root.right))
            flipColors(root);

        return root;
    }

    public Product search(int hashCode) {
        Node current = root;
        while (current != null) {
            if (hashCode < current.product.hashCode())
                current = current.left;
            else if (hashCode > current.product.hashCode())
                current = current.right;
            else
                return current.product;
        }
        return null;
    }

    public void displayAllProducts() {
        displayInOrder(root);
    }

    private void displayInOrder(Node root) {
        if (root != null) {
            displayInOrder(root.left);
            System.out.println(root.product);
            displayInOrder(root.right);
        }
    }

    private boolean isRed(Node node) {
        return node != null && node.color == Node.RED;
    }

    private Node rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = Node.RED;
        return temp;
    }

    private Node rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = Node.RED;
        return temp;
    }

    private void flipColors(Node node) {
        node.color = Node.RED;
        node.left.color = Node.BLACK;
        node.right.color = Node.BLACK;
    }

    private static class Node {
        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private Product product;
        private Node left;
        private Node right;
        private boolean color;

        public Node(Product product) {
            this.product = product;
            this.left = null;
            this.right = null;
            this.color = RED; // New nodes are always red
        }
    }
}