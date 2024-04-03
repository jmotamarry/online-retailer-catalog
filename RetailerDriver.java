import java.util.Scanner;
import java.util.Date;

public class RetailerDriver {
    public static void main(String[] args) {
        RedBlackBinaryTree catalog = new RedBlackBinaryTree();
        int id;
        for (id = 0; id < 20; id++) {
            catalog.insert(new Product("Product" + id, "Description of Product" + id, 10.0 + id, id, new Date()));
        }

        HashTable users = new HashTable();
        for (int i = 1; i <= 5; i++) {
            User user = new User("User" + i, "password" + i);
            user.getCart().insert(new Product("Product" + i, "Description of Product" + i, 10.0 + i, i, new Date()));
            users.insert(user);
        }

        Scanner scanner = new Scanner(System.in);
        User loggedIn = null;

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Log in");
            System.out.println("2. Create account");
            System.out.println("3. See all products");
            System.out.println("4. Search products");
            System.out.println("5. See your cart");
            System.out.println("6. Change password");
            System.out.println("7. Add to cart");
            System.out.println("8. Remove from cart");
            System.out.println("9. Log out");
            System.out.println("10. Add to catalog");
            System.out.println("-1. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    User user = (User) users.get(username.hashCode());
                    if (user != null && user.getPassword().equals(password)) {
                        loggedIn = user;
                        System.out.println("Logged in as " + loggedIn.getUsername());
                    } else {
                        System.out.println("Invalid username or password");
                    }
                    break;
                case 2:
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    User newUser = new User(newUsername, newPassword);
                    users.insert(newUser);
                    loggedIn = newUser;
                    System.out.println("Account created and logged in as " + loggedIn.getUsername());
                    break;
                case 3:
                    System.out.println("All products:");
                    catalog.displayAllProducts();
                    break;
                case 4:
                    System.out.print("Enter search keyword: ");
                    String keyword = scanner.nextLine();
                    int searchHash = keyword.hashCode();
                    Product foundProduct = catalog.search(searchHash);
                    if (foundProduct != null) {
                        System.out.println("Product found: " + foundProduct);
                    } else {
                        System.out.println("No product found for keyword: " + keyword);
                    }
                    break;
                case 5:
                    if (loggedIn != null) {
                        System.out.println("Your cart:");
                        loggedIn.getCart().printList();
                    } else {
                        System.out.println("Please log in to view your cart");
                    }
                    break;
                case 6:
                    if (loggedIn != null) {
                        System.out.print("Enter new password: ");
                        String newPass = scanner.nextLine();
                        loggedIn.setPassword(newPass);
                        System.out.println("Password changed successfully");
                    } else {
                        System.out.println("Please log in to change password");
                    }
                    break;
                case 7:
                    if (loggedIn != null) {
                        System.out.print("Enter product name to add to cart: ");
                        String productName = scanner.nextLine();
                        Product productToAdd = catalog.search(productName.hashCode());
                        if (productToAdd != null) {
                            loggedIn.getCart().insert(productToAdd);
                            System.out.println("Product added to cart: " + productToAdd);
                        } else {
                            System.out.println("No product found with name: " + productName);
                        }
                    } else {
                        System.out.println("Please log in to add items to your cart");
                    }
                    break;
                case 8:
                    if (loggedIn != null) {
                        System.out.print("Enter product name to remove from cart: ");
                        String productName = scanner.nextLine();
                        Product productToRemove = catalog.search(productName.hashCode());
                        if (productToRemove != null) {
                            loggedIn.getCart().delete(productName);
                            System.out.println("Product removed from cart: " + productToRemove);
                        } else {
                            System.out.println("No product found with ID: " + productName);
                        }
                    } else {
                        System.out.println("Please log in to remove items from your cart");
                    }
                    break;
                case 9:
                    loggedIn = null;
                    System.out.println("Logged out successfully.");
                    break;
                case 10:
                    System.out.println("Input the new product's name: ");
                    String name = scanner.nextLine();
                    System.out.println("Input the new product's description: ");
                    String description = scanner.nextLine();
                    System.out.println("Input the new product's price: ");
                    double price = scanner.nextDouble();
                    id += 1;
                    catalog.insert(new Product(name, description, price, id, new Date()));
                    System.out.println("Added to catalog.");
                    break;
                case -1:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again");
            }
        }
    }
}
