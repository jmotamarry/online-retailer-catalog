class User {
    private String username;
    private String password;
    private LinkedList shoppingCart;

    public User(String username, String password) {     // initializes a user with an empty shopping cart
        this.username = username;
        this.password = password;
        shoppingCart = new LinkedList();
    }

    public String getUsername() {       // returns a user's username
        return username;
    }

    public void setPassword(String newPass) {       // sets a user's password
        password = newPass;
    }

    public String getPassword() {       // gets a user's password
        return password;
    }

    public LinkedList getCart() {       // gets a user's cart
        return shoppingCart;
    }

    @Override
    public int hashCode() {     // gets the hashcode of the user's username
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {     // checks if a user object is equal to another user object
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return this.username.equals(other.username) && this.password == other.password;
    }
}
