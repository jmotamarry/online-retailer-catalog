import java.util.Arrays;

public class HashTable {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double LOAD_FACTOR_THRESHOLD = 0.7;
    private static final int QUADRATIC_CONSTANT = 5; // arbitrary constant for quadratic probing
    
    private int size;
    private int capacity;
    private User[] table;

    public HashTable() {
        this.capacity = DEFAULT_CAPACITY;
        this.table = new User[capacity];
    }

    private int hash(User user) {
        return Math.abs(user.hashCode() % capacity);
    }

    private int findEmptySlot(int hash) {
        int i = 0;
        while (table[(hash + i * i) % capacity] != null) {
            i++;
        }
        return (hash + i * i) % capacity;
    }

    private void resize() {
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

    public void insert(User user) {
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

    public User get(int hash) {
        int index = hash % capacity;
        int startIndex = index;

        while (table[index] != null && !(hash(table[index]) == hash)) {
            index = (index + QUADRATIC_CONSTANT) % capacity;
            // If we have traversed the entire array and haven't found the element, stop searching
            if (index == startIndex) {
                return null;
            }
        }

        return table[index];
    }

    public void remove(User user) {
        int hash = hash(user);
        int index = hash;

        while (table[index] != null && !table[index].equals(user)) {
            index = (index + QUADRATIC_CONSTANT) % capacity;
        }

        if (table[index] != null) {
            table[index] = null;
            size--;
        }
    }

    public int getSize() {
        return size;
    }
}