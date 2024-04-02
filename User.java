class User {
    private String username;
    private String password;
    private LinkedList shoppingCart;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        shoppingCart = new LinkedList();
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String newPass) {
        password = newPass;
    }

    public String getPassword() {
        return password;
    }

    public LinkedList getCart() {
        return shoppingCart;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return this.username.equals(other.username) && this.password == other.password;
    }
}
