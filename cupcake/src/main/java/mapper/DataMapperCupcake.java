/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import db.connector.DBConnector;
import entity.Bottom;
import entity.Cupcake;
import entity.Top;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ibenk
 */
public class DataMapperCupcake {
    
   private DBConnector conn;
    
    public void addTopping(String name, int price) throws SQLException
             {
        conn = new DBConnector();

        String insertTopping = "INSERT INTO `cupcake`.`topping` (name, price) "
                + "VALUES (?, " + price + ");";
        PreparedStatement ps = conn.getConnection().prepareStatement(insertTopping);
        ps.setString(1, name);
        ps.executeUpdate();
    }

    public void addBottom(String name, int price) throws SQLException
            {
        conn = new DBConnector();

        String insertBottom = "INSERT INTO `cupcake`.`bottom` (name, price) "
                + "VALUES (?, " + price + ");";
        PreparedStatement ps = conn.getConnection().prepareStatement(insertBottom);
        ps.setString(1, name);
        ps.executeUpdate();
    }
    
        public Cupcake makeCupcake(String topName, String bottomName) {

        Top top = getTop(topName);
        Bottom bottom = getBottom(bottomName);
        return new Cupcake(top, bottom);

    }

    public List<Top> getTops() {
        try {
            conn = new DBConnector();

            String query = "SELECT * FROM `Cupcake`.`topping`;";

            Connection connection = DBConnector.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            List<Top> toppings = new ArrayList<>();
            String name = "";
            int price = 0;

            while (rs.next()) {
                name = rs.getString("name");
                price = rs.getInt("price");
                Top top = new Top(name, price);
                toppings.add(top);
            }
            return toppings;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public List<Bottom> getBottoms() {
        try {
            conn = new DBConnector();

            String query = "SELECT * FROM `Cupcake`.`bottom`;";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            List<Bottom> bottoms = new ArrayList<>();
            String name = "";
            int price = 0;

            while (rs.next()) {
                name = rs.getString("name");
                price = rs.getInt("price");
                Bottom bottom = new Bottom(name, price);
                bottoms.add(bottom);
            }
            return bottoms;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public int getTopPrice(String name) {
        try {
            conn = new DBConnector();

            String query = "SELECT price FROM `Cupcake`.`topping` "
                    + "WHERE `cupcake`.`topping`.`name` = '" + name + "';";

            Connection connection = conn.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int price = 0;

            while (rs.next()) {
                price = rs.getInt("price");
            }
            return price;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public int getBottomPrice(String name) {
        try {
            conn = new DBConnector();

            String query = "SELECT price FROM `Cupcake`.`bottom` "
                    + "WHERE `cupcake`.`bottom`.`name` = '" + name + "';";

            Connection connection = conn.getConnection();
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

    private Top getTop(String name) {
         if (name != null) {
            int price = getTopPrice(name);
            Top top = new Top(name, price);
            return top;
        }
        return null;
    }

    private Bottom getBottom(String name) {
         if (name != null) {
            int price = getBottomPrice(name);
            Bottom bot = new Bottom(name, price);
            return bot;
        }
        return null;
    }
    
}
