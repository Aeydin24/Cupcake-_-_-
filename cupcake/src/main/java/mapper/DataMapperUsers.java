package mapper;

import entity.Users;
import db.connector.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

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
    
   /**
    * The getUser-method finds all information about the user that has the username, we give as input.
    */
    
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
    
   /**
    * The createUser-method takes a username, password and email as input.
    * Adds User to Database.
    */
    
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
    
   /**
    * The addBalance-method adds money to the users account.
    */
    
    public void addBalance(String username, int money) throws SQLException {
        try {
            dbc = new DBConnector();
        
        String insert
                = "UPDATE cupcake.users SET balance=balance + ? "
                + "WHERE username='?';";
        
        PreparedStatement ps = dbc.getConnection().prepareStatement(insert);
        
        /* Convert int money to String balance */
        String balance = String.valueOf(money);
        
        ps.setString(1, balance);
        ps.setString(2, username);
        ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataMapperUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   /**
    * The getUsers-method return a List of all Users.
    */
    
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

    public void setBalance(Users user, double userbalance) {
        try {
            String username = user.getUserName();
            String balance = String.valueOf(userbalance);
            dbc = new DBConnector();
            String auto = "SET autocommit = 0;";
            String trans = "START TRANSACTION;";
            String query = "UPDATE users SET balance = ? WHERE name = ?;";
            String commit = "COMMIT;";
            String reAuto = "SET autocommit = 1;";
            PreparedStatement ps = dbc.getConnection().prepareStatement(auto);
            ps.executeUpdate();
            ps = dbc.getConnection().prepareStatement(trans);
            ps.executeUpdate();
            ps = dbc.getConnection().prepareStatement(query);
            ps.setString(1, balance);
            ps.setString(2, username);
            ps.executeUpdate();
            ps = dbc.getConnection().prepareStatement(commit);
            ps.executeUpdate();
            ps = dbc.getConnection().prepareStatement(reAuto);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(DataMapperUsers.class.getName()).log(Level.SEVERE, null, ex);
            try {
                String rollBack = "ROLLBACK;";
                PreparedStatement ps = dbc.getConnection().prepareStatement(rollBack);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
