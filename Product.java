import java.util.Date;

class Product {
    private String name;
    private String description;
    private double price;
    private int id;
    private Date dateListed;

    public Product(String name, String description, double price, int id, Date dateListed) {        // initlializes product object
        this.name = name;
        this.description = description;
        this.price = price;
        this.id = id;
        this.dateListed = dateListed;
    }

    public int getId() {        // return product ID
        return id;
    }

    public String getName() {       // return product name
        return name;
    }

    public int hashCode() {     // return hashcode of the username
        return name.hashCode();
    }

    @Override
    public String toString() {      // prints out product information when toString is called
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", id=" + id +
                ", dateListed=" + dateListed +
                '}';
    }
}
