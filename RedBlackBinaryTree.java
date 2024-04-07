public class RedBlackBinaryTree {
    private Node root;

    public RedBlackBinaryTree() {       // initializes red black tree with a null root
        this.root = null;
    }

    public void insert(Product product) {       // inserts a product into the tree using insertRecursive function
        root = insertRecursive(root, product);
        root.color = Node.BLACK;
    }

    private Node insertRecursive(Node root, Product product) {      // recursively inserts a product into the red black tree based on the name's has code
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

    public Product search(int hashCode) {       // searches through the red black tree for products based on the name's hash code
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

    public void displayAllProducts() {      // displays all the products in red black tree using displayInOrder function
        displayInOrder(root);
    }

    private void displayInOrder(Node root) {        // displays the products in order
        if (root != null) {
            displayInOrder(root.left);
            System.out.println(root.product);
            displayInOrder(root.right);
        }
    }

    private boolean isRed(Node node) {      // checks if a node is red
        return node != null && node.color == Node.RED;
    }

    private Node rotateLeft(Node node) {        // rotates left to keep RBT properties
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = Node.RED;
        return temp;
    }

    private Node rotateRight(Node node) {       // rotates right to keep RBT properties
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = Node.RED;
        return temp;
    }

    private void flipColors(Node node) {        // flips a node's color to keep RBT properties
        node.color = Node.RED;
        node.left.color = Node.BLACK;
        node.right.color = Node.BLACK;
    }

    private static class Node {     // creates a node for red black tree with a product, a left, a right, and a color
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