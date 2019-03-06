/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ndupo
 */
public class DataBaseConnectionTest 
{
        public static void main(String[] args) {
            String driver = "com.mysql.cj.jdbc.Driver";
            String Url = "jdbc:mysql://localhost:3306/cupcake?UseSSL=false";
            String user = "root";
            String password = "1234";
            Connection conn = null;
                if (conn == null)
            {
            try
            {
                Class.forName(driver).newInstance();
                System.out.println("Trying to connect");
                conn = DriverManager.getConnection(Url, user, password);
                System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
                        + conn.getMetaData().getDatabaseProductName());
            }
            catch (Exception ex)
            {
                System.out.println("Unable to make connection with DB");
                ex.printStackTrace();
            }
        }
    }
}
