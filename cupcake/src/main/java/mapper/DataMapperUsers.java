package mapper;

import entity.Users;
import db.connector.DBConnector;
import entity.Bottom;
import entity.Cupcake;
import entity.Top;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import shopping.LineItem;
import shopping.ShoppingCart;

/**
 *
 * @author ibenk
 */
public class DataMapperUsers 
{

    private DBConnector dbc;

    public DataMapperUsers() throws SQLException {
        this.dbc = new DBConnector();
    }
    
    // The method getUser finds all information about the user, we give as input.
    
    public Users getUser(String username) throws SQLException {
        
        Users user = new Users();

        dbc = new DBConnector();

        String query = "SELECT * FROM cupcake.users "
                + "WHERE `username`='" + username + "';";

        Connection connection = dbc.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String password = rs.getString("password");
            user.setPassword(password);
            int balance = rs.getInt("balance");
            user.setBalance(balance);
            String email = rs.getString("email");
            user.setEmail(email);
        }
        user.setUserName(username);
        return user;
    }
   
    // The method createUser takes username, password and email as input, and adds the user to the database.
    
    public void createUser(String username, String password, String email) throws SQLException 
    {
       if (username != null || password != null || email != null)
       {    
        try {
            dbc = new DBConnector();
            String addUser
                    = "INSERT INTO cupcake.users (`username`, `password`, `balance`, `email` ) "
                    + "VALUES(?,?,0,?);";

            PreparedStatement ps = dbc.getConnection().prepareStatement(addUser);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.executeUpdate();
            } catch (SQLException ex) {
          Logger.getLogger(DataMapperUsers.class.getName()).log(Level.SEVERE, null, ex);  
            }
        }
    }
    
    // The method addBalance adds moeny to the user in session account.
    
    public void addBalance(String username, int money) throws SQLException {
        try {
            dbc = new DBConnector();
        
        String insertBalance = "UPDATE `cupcake`.`users` SET balance = ? WHERE username = ?;";
        
        PreparedStatement ps = dbc.getConnection().prepareStatement(insertBalance);
        
        /* Convert int money to String balance */
        String balance = String.valueOf(money);
        
        ps.setString(2, username);
        ps.setString(1, balance);
        ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataMapperUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // The method getUsers returns a list of all users.
    
    public List<Users> getUsers() throws SQLException {
        List<Users> users = new ArrayList<>();
        
        dbc = new DBConnector();
        
        String query
                = "SELECT * FROM cupcake.users;";
        
        Connection connection = dbc.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");
            int balance = rs.getInt("balance");
            String email = rs.getString("email");
            Users user = new Users(username, password, balance, email);
            users.add(user);
        }
        
        return users;
    }

    // The method setBalance sets the users balance.
    public void setBalance(Users user, int userbalance) {
        try {
            String username = user.getUserName();
            String balance = String.valueOf(userbalance);
            dbc = new DBConnector();
            String query = "UPDATE `cupcake`.`users` SET balance = ? WHERE name = ?;";
            PreparedStatement ps = dbc.getConnection().prepareStatement(query);
            ps.executeUpdate();
            ps.setString(1, balance);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DataMapperUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // The method addInvoice adds an invoice of the user in session to the database.
    public void addInvoice(Users user) {
        try {
            dbc = new DBConnector();
            
            String name = user.getUserName();
            String insertBalance = "INSERT INTO `cupcake`.`invoices` (username) VALUES (?);";
            PreparedStatement ps = dbc.getConnection().prepareStatement(insertBalance);
            ps.setString(1, name);
   
            ps.executeUpdate();

            ShoppingCart cart = user.getCart();
            for (LineItem item : cart.getLineItems()) {
                addOrder(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataMapperUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // The method addOrder adds an orderdetail to the database.
    public void addOrder(LineItem item) throws SQLException {
        dbc = new DBConnector();

        Cupcake cake = item.getCupcake();
        Top top = cake.getTop();
        Bottom bot = cake.getBottom();

        String tname = top.getName();
        String bname = bot.getName();
        int qty = item.getQuantity();

        String query = "INSERT INTO `cupcake`.`orderdetails` (tname, bname, qty)"
                + "VALUES (?, ?, ?)";

        PreparedStatement ps = dbc.getConnection().prepareStatement(query);
        ps.setString(1, tname);
        ps.setString(2, bname);
        ps.setInt(3, qty);
        ps.executeUpdate();
    }
}
