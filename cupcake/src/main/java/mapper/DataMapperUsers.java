package mapper;

import entity.Users;
import db.connector.DBConnector;
import db.connector.databaseconnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author ibenk
 */
public class DataMapperUsers {

    private databaseconnect dbc = new databaseconnect();
    
    public void setDataSource(DataSource ds)
    {
        dbc.setDataSource(ds);
    }
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

            while (rs.next()) {
                id = rs.getInt("iduser");
                password = rs.getString("password");
                balance = rs.getInt("balance");
                email = rs.getString("email");
                admin = rs.getBoolean("isadmin");

                user = new Users(username, password, balance, admin, email);
            }
            return user;

        } catch (Exception ex) {
            Logger.getLogger(DataMapperUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /*
    The createUser-method takes a username, password, boolean and email as input.
    iduser has AI in mysql, so we don't need to type it. 
     */
    public Users createUser(String username, String password, int balance, boolean admin, String email) throws SQLException {
        try {
            dbc.open();
            

            String createUser
                    = "INSERT INTO cupcake.users (username, password, balance, admin, email ) "
                    + "VALUES(?,?,?,?,?);";

            PreparedStatement ps = dbc.preparedStatement(createUser, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setDouble(3, balance);
            ps.setBoolean(4, admin);
            ps.setString(5, email);

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();

            if (resultSet.next()) {
                return new Users(username, password, balance, admin, email);
            }
            dbc.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
