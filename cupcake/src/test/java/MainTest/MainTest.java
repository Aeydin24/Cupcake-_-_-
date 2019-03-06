/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainTest;

import db.connector.DBConnector;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.DataMapperUsers;

/**
 *
 * @author ndupo
 */
public class MainTest 
{
    private DBConnector dbc;
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
            System.out.println("User Created and added");
            } catch (SQLException ex) {
                
          Logger.getLogger(DataMapperUsers.class.getName()).log(Level.SEVERE, null, ex);  
            System.out.println("Error");
            }
        }
    }
    public static void main(String[] args) throws SQLException {
        MainTest mt = new MainTest();
        mt.createUser("Test2", "Test2", "Test2@Test.com");
    }
}
