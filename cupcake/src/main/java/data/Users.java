package data;

/**
 *
 * @author ibenk
 */
public class Users {
 private int id;
    private String username;
    private String password;
    private int balance;
    private String email;

    public Users(int id, String username, String password, int balance, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString() {
        return "Userid: " + id + ", username: " + username + ", password: " + password + ", balance: " + balance + ", email: " + email;
    }
    
}
