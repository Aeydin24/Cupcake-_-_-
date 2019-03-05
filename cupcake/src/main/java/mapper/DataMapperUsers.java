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
    
    public Users getUser(String username) throws SQLException {
        
        try {
            DBConnector conn = new DBConnector();
            Statement statement = conn.getConnection().createStatement();
            String query
                    = "SELECT * "
                    + "FROM cupcake.user "
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

    public void createUser(String username, String password, String email) throws SQLException {
        DBConnector conn = new DBConnector();

        String createUser 
                = "INSERT INTO cupcake.user"
                + "VALUES (" + username + ", " + password + ", 0, " + email + ");";
        PreparedStatement ps = conn.getConnection().prepareStatement(createUser);
        ps.executeUpdate();
    }
    

}
