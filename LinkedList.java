public class LinkedList {
    private Node head;
    private int size;

    public LinkedList() {       // initializes linked list
        this.head = null;
        this.size = 0;
    }

    public void insert(Product product) {       // inserts product at the front of the linked list
        Node newNode = new Node(product);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void delete(String name) {       // deletes the product with the name parameter name
        if (head == null)
            return;

        if (head.product.getName().equals(name)) {
            head = head.next;
            size--;
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.product.getName().equals(name)) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    public void printList() {       // prints out the whole linked list
        Node current = head;
        while (current != null) {
            System.out.println(current.product);
            current = current.next;
        }
    }

    public int getSize() {      // returns the size of the linked list
        return size;
    }

    private static class Node {     // creates linked list nodes with a product and a next node
        private Product product;
        private Node next;

        public Node(Product product) {      // initializes nodes with a product and a null next node
            this.product = product;
            this.next = null;
        }
    }
}