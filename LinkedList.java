public class LinkedList {
    private Node head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void insert(Product product) {
        Node newNode = new Node(product);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void delete(int id) {
        if (head == null)
            return;

        if (head.product.getId() == id) {
            head = head.next;
            size--;
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.product.getId() == id) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println(current.product);
            current = current.next;
        }
    }

    public int getSize() {
        return size;
    }

    private static class Node {
        private Product product;
        private Node next;

        public Node(Product product) {
            this.product = product;
            this.next = null;
        }
    }
}