import java.util.Arrays;

public class HashTable {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double LOAD_FACTOR_THRESHOLD = 0.7;
    private static final int LINEAR_CONSTANT = 1; // constant for linear probing
    
    private int size;
    private int capacity;
    private User[] table;

    public HashTable() {        // sets capacity to default capacity and table to an empty array of users
        this.capacity = DEFAULT_CAPACITY;
        this.table = new User[capacity];
    }

    private int hash(User user) {       // returns the hashcode of the username of user objects
        return Math.abs(user.getUsername().hashCode() % capacity);
    }

    private int findEmptySlot(int hash) {       // finds an empty slot in User array using linear probing for adding to hash table
        int index = hash % capacity;
        while (table[index] != null) {
            index = (index + LINEAR_CONSTANT) % capacity;
        }
        return index;
    }

    public User get(String username) {      // gets user object from hash table based on their username
        int hash = Math.abs(username.hashCode() % capacity);
        int index = hash;

        while (table[index] != null && !table[index].getUsername().equals(username)) {
            index = (index + LINEAR_CONSTANT) % capacity;
            // If we have traversed the entire array and haven't found the element, stop searching
            if (index == hash) {
                return null;
            }
        }

        return table[index];
    }

    private void resize() {     // resizes the user array when the load factor goes over 0.7
        capacity *= 2;
        User[] oldTable = Arrays.copyOf(table, capacity);
        table = new User[capacity];
        size = 0;

        for (User user : oldTable) {
            if (user != null) {
                insert(user);
            }
        }
    }

    public void insert(User user) {     // inserts user objects into hash table using linear probing
        if ((double) size / capacity > LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int hash = hash(user);
        int index = hash;

        if (table[index] != null) {
            index = findEmptySlot(hash);
        }

        table[index] = user;
        size++;
    }

    public void remove(User user) {     // removes user objects from hash table
        String username = user.getUsername();
        int hash = Math.abs(username.hashCode() % capacity);
        int index = hash;

        while (table[index] != null && !table[index].getUsername().equals(username)) {
            index = (index + LINEAR_CONSTANT) % capacity;
        }

        if (table[index] != null) {
            table[index] = null;
            size--;
        }
    }

    public int getSize() {      // returns the number of user objects in the hash tablel
        return size;
    }
}
