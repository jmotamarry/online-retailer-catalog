import java.util.Scanner;
import java.util.Date;
import java.util.*;

public class RetailerDriver {
    public static void main(String[] args) {
        final String WAREHOUSE = "Bakersfield";
        WeightedGraph cityGraph = new WeightedGraph();      // creates a weighted graph as a city map and adds default values

        cityGraph.addNode(new WeightedGraphNode("San Francisco"));
        cityGraph.addNode(new WeightedGraphNode("San Jose"));
        cityGraph.addNode(new WeightedGraphNode("Bakersfield"));
        cityGraph.addNode(new WeightedGraphNode("Fresno"));
        cityGraph.addNode(new WeightedGraphNode("Oakland"));
        cityGraph.addNode(new WeightedGraphNode("Los Angeles"));
        cityGraph.addNode(new WeightedGraphNode("San Diego"));
        cityGraph.addNode(new WeightedGraphNode("Fremont"));
        cityGraph.addNode(new WeightedGraphNode("Sacramento"));
        cityGraph.addNode(new WeightedGraphNode("Redwood City"));

        cityGraph.addEdge(new WeightedGraphNode("San Francisco"), new WeightedGraphNode("San Jose"), 3);
        cityGraph.addEdge(new WeightedGraphNode("San Francisco"), new WeightedGraphNode("San Diego"), 6);
        cityGraph.addEdge(new WeightedGraphNode("Redwood City"), new WeightedGraphNode("San Jose"), 1);
        cityGraph.addEdge(new WeightedGraphNode("Bakersfield"), new WeightedGraphNode("Redwood City"), 5);
        cityGraph.addEdge(new WeightedGraphNode("Bakersfield"), new WeightedGraphNode("San Jose"), 5);


        RedBlackBinaryTree catalog = new RedBlackBinaryTree();      // creates an RBT as catalog for products
        int id;
        for (id = 0; id < 20; id++) {       // creates 20 default products
            catalog.insert(new Product("Product" + id, "Description of Product" + id, 10.0 + id, id, new Date()));
        }

        HashTable users = new HashTable();
        for (int i = 1; i <= 5; i++) {      // creates 5 default users
            User user = new User("User" + i, "password" + i);
            user.getCart().insert(new Product("Product" + i, "Description of Product" + i, 10.0 + i, i, new Date()));
            users.insert(user);
        }

        Scanner scanner = new Scanner(System.in);
        User loggedIn = null;       // stores a user object if a user logs in

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
            System.out.println("11. See delivery time based on city");
            System.out.println("12. Print all the cities and their connections");
            System.out.println("13. Add a city to the graph");
            System.out.println("14. Add a new path between cities");
            System.out.println("15. See how long it takes to ship from one city to another");
            System.out.println("-1. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:     // checks the user hash table to see if the username and password match and puts that object in loggedIn
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    User user = (User) users.get(username);
                    if (user != null && user.getPassword().equals(password)) {
                        loggedIn = user;
                        System.out.println("Logged in as " + loggedIn.getUsername());
                    } else {
                        System.out.println("Invalid username or password");
                    }
                    break;
                case 2:     // creates and adds a new user object to user hash table
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    User newUser = new User(newUsername, newPassword);
                    users.insert(newUser);
                    loggedIn = newUser;
                    System.out.println("Account created and logged in as " + loggedIn.getUsername());
                    break;
                case 3:     // displays all the products in RBT
                    System.out.println("All products:");
                    catalog.displayAllProducts();
                    break;
                case 4:     // searches RBT for a product based on the hash code of the name
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
                case 5:     // displays all the products in the user's linked list cart
                    if (loggedIn != null) {
                        System.out.println("Your cart:");
                        loggedIn.getCart().printList();
                    } else {
                        System.out.println("Please log in to view your cart");
                    }
                    break;
                case 6:     // changes a user object's password
                    if (loggedIn != null) {
                        System.out.print("Enter new password: ");
                        String newPass = scanner.nextLine();
                        loggedIn.setPassword(newPass);
                        System.out.println("Password changed successfully");
                    } else {
                        System.out.println("Please log in to change password");
                    }
                    break;
                case 7:     // adds a product from the red black tree catalog to the current user's cart
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
                case 8:     // removes a product in the current user's cart based on product name
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
                case 9:     // changes the current user to null
                    loggedIn = null;
                    System.out.println("Logged out successfully.");
                    break;
                case 10:        // adds a new product to the RBT catalog
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
                case 11:
                    System.out.println("Input the name of the city you are in: ");
                    String city = scanner.nextLine().toLowerCase();
                    List<WeightedGraphNode> path = cityGraph.shortestPath(WAREHOUSE, city);
                    try {
                        System.out.println("");
                        for (int i = 0; i < path.size() - 2; i++) {
                            System.out.print(path.get(i).getCityName() + " -> ");
                        }
                        System.out.println(path.get(path.size() - 2).getCityName());
                        System.out.println("All packages will take " + Integer.valueOf(path.get(path.size() - 1).getCityName()) + " days to get to you.");
                    } catch (Exception e) {
                        System.out.println("\nThat city is not in the graph or there is no path to that city from " + WAREHOUSE + ".");
                    }
                    break;
                case 12:
                    cityGraph.printGraph();
                    break;
                case 13:
                    System.out.println("What do you want the new city to be called: ");
                    String newCity = scanner.nextLine();
                    cityGraph.addNode(new WeightedGraphNode(newCity));
                    break;
                case 14:
                    System.out.println("Start city: ");
                    String startCity = scanner.nextLine();
                    System.out.println("End city: ");
                    String endCity = scanner.nextLine();
                    System.out.println("Days to travel: ");
                    int daysToTravel = scanner.nextInt();
                    cityGraph.addEdge(new WeightedGraphNode(startCity), new WeightedGraphNode(endCity), daysToTravel);
                    break;
                case 15:
                    System.out.println("Start city: ");
                    String firstCity = scanner.nextLine();
                    System.out.println("End city: ");
                    String secondCity = scanner.nextLine();
                    List<WeightedGraphNode> shortest = cityGraph.shortestPath(firstCity, secondCity);
                    try {
                        System.out.println("");
                        for (int i = 0; i < shortest.size() - 2; i++) {
                            System.out.print(shortest.get(i).getCityName() + " -> ");
                        }
                        System.out.println(shortest.get(shortest.size() - 2).getCityName());
                        System.out.println("All packages will take " + Integer.valueOf(shortest.get(shortest.size() - 1).getCityName()) + " days to get there.");
                    } catch (Exception e) {
                        System.out.println("\nThat city is not in the graph or there is no path to that city from " + firstCity + ".");
                    }
                    break;
                case -1:        // exits the program on input -1
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again");
            }
        }
    }
}
