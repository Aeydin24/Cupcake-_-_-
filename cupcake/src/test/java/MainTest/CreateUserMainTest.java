/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainTest;

import db.connector.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.DataMapperUsers;

/**
 *
 * @author ndupo
 */
public class CreateUserMainTest 
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
    public void addTopping(String name, int price) throws SQLException
             {
        dbc = new DBConnector();

        String insertTopping = "INSERT INTO `cupcake`.`toppings` (name, price) "
                + "VALUES (?, " + price + ");";
        PreparedStatement ps = dbc.getConnection().prepareStatement(insertTopping);
        ps.setString(1, name);
        ps.executeUpdate();
                 System.out.println("Topping added to database");
    }
    public void addBottom(String name, int price) throws SQLException
            {
        dbc = new DBConnector();

        String insertBottom = "INSERT INTO `cupcake`.`bottoms` (name, price) "
                + "VALUES (?, " + price + ");";
        PreparedStatement ps = dbc.getConnection().prepareStatement(insertBottom);
        ps.setString(1, name);
        ps.executeUpdate();
                System.out.println("bottom added to database");
    }
    public int getBottomPrice(String name) {
        try {
            dbc = new DBConnector();

            String query = "SELECT price FROM `Cupcake`.`bottoms` "
                    + "WHERE `cupcake`.`bottoms`.`name` = '" + name + "';";

            Connection connection = dbc.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int price = 0;

            while (rs.next()) {
                price = rs.getInt("Price");
            }
            return price;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }
    
    public static void main(String[] args) throws SQLException {
        CreateUserMainTest mt = new CreateUserMainTest();
        
    }
}
