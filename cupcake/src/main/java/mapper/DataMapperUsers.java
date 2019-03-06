package mapper;

import entity.Users;
import db.connector.DBConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ibenk
 */
public class DataMapperUsers {
    
    /*
    The getUser-method finds all information about the user that has the username, we give as input.
    */
    
    public Users getUser(String username) throws SQLException {
        try {
            DBConnector conn = new DBConnector();
            Statement statement = conn.getConnection().createStatement();
            String query
                    = "SELECT * "
                    + "FROM cupcake.users "
                    + "WHERE username='" + username + "'";
            
            ResultSet rs = statement.executeQuery(query);
            Users user = null;
            int id;
            String password;
            int balance;
            String email;
            boolean admin;
            
            while(rs.next()) {
                id = rs.getInt("iduser");
                password = rs.getString("password");
                balance = rs.getInt("balance");
                email = rs.getString("email");
                admin = rs.getBoolean("isadmin");
                
                user = new Users(id, username, password, balance, admin, email);
            }
            return user;
            
        } catch (Exception ex) {
            Logger.getLogger(DataMapperUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /*
    The createUser-method takes a username, password, boolean and email as input.
    iduser is AI in mysql.
    */
    
    public void createUser(String username, String password, int balance, boolean admin, String email) throws SQLException {
        DBConnector conn = new DBConnector();

        String createUser 
                = "INSERT INTO cupcake.users"
                + "VALUES (" + username + ", " + password + ", " + balance + ", " + admin + ", " + email + ");";
        PreparedStatement ps = conn.getConnection().prepareStatement(createUser);
        ps.executeUpdate();
    }
    

}
